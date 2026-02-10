package com.jsaldana.superheroapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jsaldana.superheroapp.MainViewModel
import com.jsaldana.superheroapp.SuperheroState
import com.jsaldana.superheroapp.data.model.SuperHero

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroDetailScreen(
	heroId: Int,
	viewModel: MainViewModel,
	onBack: () -> Unit
) {
	// 1. Disparar la carga del héroe al entrar
	LaunchedEffect(heroId) {
		viewModel.getHeroByID(heroId)
	}

	val state = viewModel.detailState

	Scaffold(
		containerColor = Color(0xFFF5F3E9) // Fondo crema
	) { padding ->
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(padding)
		) {
			when (state) {
				is SuperheroState.Loading -> {
					CircularProgressIndicator(Modifier.align(Alignment.Center))
				}

				is SuperheroState.Success -> {
					// Extraemos el héroe de la lista (asumiendo que viene uno solo)
					val hero = state.heroes.firstOrNull()
					if (hero != null) {
						// 2. Llamamos al contenido con el diseño visual
						HeroDetailContent(hero = hero, onBack = onBack)
					}
				}

				is SuperheroState.Error -> {
					Text(state.message, Modifier.align(Alignment.Center), color = Color.Red)
				}

				else -> {}
			}
		}
	}
}

@Composable
fun HeroDetailContent(hero: SuperHero, onBack: () -> Unit) {
	LazyColumn(modifier = Modifier.fillMaxSize()) {
		// Cabecera: Imagen + Botón Back
		item {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(450.dp)
			) {
				AsyncImage(
					model = hero.image,
					contentDescription = null,
					modifier = Modifier.fillMaxSize(),
					contentScale = ContentScale.Crop
				)

				// Botón back circular sobre la imagen
				IconButton(
					onClick = onBack,
					modifier = Modifier
						.padding(top = 48.dp, start = 24.dp)
						.size(56.dp)
						.background(Color(0xFF2D3135).copy(alpha = 0.8f), CircleShape)
				) {
					Icon(
						imageVector = Icons.AutoMirrored.Filled.ArrowBack,
						contentDescription = "Back",
						tint = Color(0xFFFFC107),
						modifier = Modifier.size(28.dp)
					)
				}
			}
		}

		// Bloque de información
		item {
			Column(modifier = Modifier.padding(24.dp)) {
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					Text(
						text = hero.name,
						style = MaterialTheme.typography.displayMedium.copy(
							fontWeight = FontWeight.ExtraBold,
							color = Color(0xFF2D3135)
						)
					)
					// Logo circular placeholder
					Surface(
						modifier = Modifier.size(40.dp),
						shape = CircleShape,
						color = Color.White,
						shadowElevation = 4.dp
					) {
						Icon(
							Icons.Default.Public,
							null,
							tint = Color.Red,
							modifier = Modifier.padding(8.dp)
						)
					}
				}

				HorizontalDivider(
					modifier = Modifier.padding(vertical = 16.dp),
					thickness = 2.dp,
					color = Color(0xFF2D3135)
				)

				// Stats y Datos técnicos
				//DetailRow(label = "Race:", value = hero.race)
				DetailRow(label = "Gender:", value = hero.gender)

				Spacer(modifier = Modifier.height(16.dp))

				Text(
					"Work:",
					style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
				)
				Text(hero.work, style = MaterialTheme.typography.bodyLarge)
			}
		}
	}
}

@Composable
fun DetailRow(label: String, value: String) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 4.dp),
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Text(label, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
		Text(value, style = MaterialTheme.typography.bodyLarge)
	}
}