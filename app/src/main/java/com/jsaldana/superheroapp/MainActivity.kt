package com.jsaldana.superheroapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.jsaldana.superheroapp.data.model.SuperHero
import com.jsaldana.superheroapp.ui.theme.SuperHeroAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			SuperHeroAppTheme {
				Scaffold(
					modifier = Modifier.fillMaxSize(),
					topBar = {
						// Opcional: Podrías añadir un TopAppBar aquí
					}
				) { innerPadding ->
					SuperheroScreen(modifier = Modifier.padding(innerPadding))
				}
			}
		}
	}
}


@Composable
fun SuperheroScreen(
	modifier: Modifier = Modifier,
	viewModel: MainViewModel = koinViewModel()
) {
	val state = viewModel.state

	LaunchedEffect(Unit) {
		viewModel.getAllHeroes()
	}

	Box(modifier = modifier.fillMaxSize()) {
		when (state) {
			is SuperheroState.Loading -> {
				CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
			}

			is SuperheroState.Success -> {
				HeroList(heroes = state.heroes)
			}

			is SuperheroState.Error -> {
				ErrorMessage(
					message = state.message,
					onRetry = { viewModel.getAllHeroes() },
					modifier = Modifier.align(Alignment.Center)
				)
			}

			else -> {}
		}
	}
}

@Composable
fun HeroList(heroes: List<SuperHero>) {
	LazyColumn(
		modifier = Modifier.fillMaxSize(),
		contentPadding = PaddingValues(16.dp), // Padding alrededor de toda la lista
		verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio uniforme entre items
	) {
		items(
			items = heroes,
			key = { it.id } // Importante para rendimiento en LazyColumn
		) { hero ->
			HeroItem(hero = hero)
		}
	}
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHeroList() {
	val mockHeroes = listOf(
		SuperHero(
			1,
			"Batman",
			"https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/1-a-bomb.jpg",
			"Hombre de negocios"
		),
		SuperHero(
			2,
			"Superman",
			"https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/xs/1-a-bomb.jpg",
			"Cientifico"
		),
		SuperHero(3, "Wonder Woman", "", "Princesa de las Amazonas")
	)
	SuperHeroAppTheme {
		HeroList(heroes = mockHeroes)
	}
}


@Composable
fun HeroItem(hero: SuperHero, modifier: Modifier = Modifier) {
	Card(
		modifier = modifier
			.fillMaxWidth()
			.padding(vertical = 8.dp),
		elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
					label = { Text(hero.work) },
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

@Preview(showBackground = true, name = "Hero Item Light")
@Preview(
	showBackground = true,
	uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
	name = "Hero Item Dark"
)
@Composable
fun PreviewHeroItem() {
	SuperHeroAppTheme {
		HeroItem(
			hero = SuperHero(id = 1, name = "Spider-Man", image = "", work = "Cientifico")
		)
	}
}

@Composable
fun ErrorMessage(
	message: String,
	onRetry: () -> Unit,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier.padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(
			text = "¡Ups! Algo salió mal",
			style = MaterialTheme.typography.headlineSmall,
			color = MaterialTheme.colorScheme.error
		)
		Spacer(modifier = Modifier.height(8.dp))
		Text(
			text = message,
			style = MaterialTheme.typography.bodyMedium,
			textAlign = TextAlign.Center
		)
		Spacer(modifier = Modifier.height(16.dp))
		Button(onClick = onRetry) {
			Text(text = "Reintentar")
		}
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorMessage() {
	SuperHeroAppTheme {
		ErrorMessage(
			message = "No se pudo conectar al servidor. Revisa tu conexión.",
			onRetry = { /* Nada en preview */ }
		)
	}
}