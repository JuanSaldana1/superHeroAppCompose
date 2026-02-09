package com.jsaldana.superheroapp.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.jsaldana.superheroapp.SuperheroState
import com.jsaldana.superheroapp.data.model.SuperHero
import com.jsaldana.superheroapp.ui.theme.SuperHeroAppTheme

@Composable
fun SuperheroListScreen(state: SuperheroState, onLoad: () -> Unit, onHeroClick: (Long) -> Unit) {
	LaunchedEffect(Unit) { onLoad() }

	Surface(
		modifier = Modifier.fillMaxSize(),
		color = MaterialTheme.colorScheme.background
	) {
		Box(Modifier.fillMaxSize()) {
			when (state) {
				is SuperheroState.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
				is SuperheroState.Success -> {
					LazyColumn {
						items(state.heroes) { hero ->
							Card(
								modifier = Modifier
									.fillMaxWidth()
									.padding(12.dp),
								elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
								onClick = { onHeroClick(hero.id) }
							) {
								Row(
									modifier = Modifier.padding(16.dp),
									verticalAlignment = Alignment.CenterVertically
								) {
									AsyncImage(
										model = ImageRequest.Builder(LocalContext.current)
											.data(hero.image) // Asegúrate de que tu modelo tenga la URL aquí
											.crossfade(true)
											.build(),
										contentDescription = "Imagen de ${hero.name}",
										modifier = Modifier
											.size(80.dp)
											.clip(CircleShape), // Imagen circular
										contentScale = ContentScale.Crop
									)

									Spacer(modifier = Modifier.width(16.dp))
									Column() {
										Text(
											text = hero.name,
											style = MaterialTheme.typography.titleLarge
										)
										Spacer(modifier = Modifier.height(4.dp))
										AssistChip(
											onClick = { Log.d("Assist chip", "hello world") },
											label = {
												Text(
													text = hero.work,
													maxLines = 1, // Evita que el texto largo rompa el diseño
													overflow = TextOverflow.Ellipsis
												)
											},
											leadingIcon = {
												Icon(
													Icons.Filled.Work,
													contentDescription = "Localized description",
													Modifier.size(AssistChipDefaults.IconSize)
												)
											}
										)
									}
								}
							}
						}
					}
				}

				is SuperheroState.Error -> Text(state.message, Modifier.align(Alignment.Center))
				else -> {}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroDetailScreen(hero: SuperHero, onBack: () -> Unit) {
	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text(hero.name) },
				navigationIcon = {
					IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }
				}
			)
		}
	) { padding ->
		Column(Modifier.padding(padding)) {
			AsyncImage(
				model = hero.image,
				contentDescription = null,
				modifier = Modifier
					.fillMaxWidth()
					.height(400.dp),
				contentScale = ContentScale.Crop
			)
			Text(
				"Race: ${hero.race}",
				Modifier.padding(16.dp),
				style = MaterialTheme.typography.headlineSmall
			)
			Text("Gender: ${hero.gender}", Modifier.padding(horizontal = 16.dp))
		}
	}
}

@Preview(showBackground = true)
@Composable
fun SuperheroListScreenSuccessPreview() {
	SuperHeroAppTheme {
		SuperheroListScreen(
			state = SuperheroState.Success(SampleData.heroes),
			onLoad = {},
			onHeroClick = {}
		)
	}
}

@Preview(showBackground = true)
@Composable
fun SuperheroListScreenLoadingPreview() {
	SuperHeroAppTheme {
		SuperheroListScreen(
			state = SuperheroState.Loading,
			onLoad = {},
			onHeroClick = {}
		)
	}
}

@Preview(showBackground = true)
@Composable
fun SuperheroListScreenErrorPreview() {
	SuperHeroAppTheme {
		SuperheroListScreen(
			state = SuperheroState.Error("Something went wrong"),
			onLoad = {},
			onHeroClick = {}
		)
	}
}