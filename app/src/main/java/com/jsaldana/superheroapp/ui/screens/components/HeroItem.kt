package com.jsaldana.superheroapp.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jsaldana.superheroapp.data.model.SuperHero

@Composable
fun HeroCardLarge(hero: SuperHero, onClick: (Long) -> Unit) {
	Card(
		modifier = Modifier
			.width(280.dp)
			.height(400.dp)
			.padding(8.dp)
			.clickable { onClick(hero.id) },
		shape = RoundedCornerShape(48.dp), // Esquinas muy redondeadas como la imagen
		elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
	) {
		Box(modifier = Modifier.fillMaxSize()) {
			AsyncImage(
				model = hero.image,
				contentDescription = null,
				modifier = Modifier.fillMaxSize(),
				contentScale = ContentScale.Crop
			)

			// Texto superpuesto en la parte inferior
			Text(
				text = hero.name,
				modifier = Modifier
					.align(Alignment.BottomStart)
					.padding(start = 24.dp, bottom = 32.dp),
				style = MaterialTheme.typography.headlineMedium.copy(
					color = Color.White,
					fontWeight = FontWeight.Bold
				)
			)
		}
	}
}