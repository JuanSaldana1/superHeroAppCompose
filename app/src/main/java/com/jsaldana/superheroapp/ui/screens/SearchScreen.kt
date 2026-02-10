package com.jsaldana.superheroapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.jsaldana.superheroapp.MainViewModel
import com.jsaldana.superheroapp.data.model.SuperHero

@Composable
fun SearchScreen(
	viewModel: MainViewModel,
	onHeroClick: (Long) -> Unit
) {
	val searchText = viewModel.searchText
	val filteredHeroes = viewModel.filteredHeroes

	// La pantalla de búsqueda se construirá sobre un fondo crema
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(Color(0xFFF5F3E9)) // Color de fondo crema
			.padding(horizontal = 16.dp, vertical = 24.dp)
	) {
		// Título "search" en grande y negrita
		Text(
			text = "search",
			style = MaterialTheme.typography.displayMedium.copy(
				fontWeight = FontWeight.ExtraBold,
				color = Color(0xFF2D3135) // Color de texto oscuro
			)
		)

		Spacer(modifier = Modifier.height(16.dp))

		// Barra de búsqueda personalizada
		SearchBar(searchText = searchText, onSearchTextChange = viewModel::onSearchTextChange)

		Spacer(modifier = Modifier.height(16.dp))

		// Lista de resultados de la búsqueda
		LazyColumn(
			modifier = Modifier.fillMaxSize(),
			verticalArrangement = Arrangement.spacedBy(0.dp) // Los dividers ya añaden separación
		) {
			items(filteredHeroes, key = { it.id }) { hero ->
				SearchItem(hero = hero, onClick = onHeroClick)
				HorizontalDivider(
					color = Color.Gray.copy(alpha = 0.3f),
					thickness = 1.dp
				)
			}
		}
	}
}

// Componente para la barra de búsqueda
@Composable
fun SearchBar(
	searchText: String,
	onSearchTextChange: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	Surface(
		modifier = modifier
			.fillMaxWidth()
			.height(56.dp),
		shape = CircleShape, // Forma redondeada
		color = Color(0xFF2D3135) // Fondo oscuro
	) {
		Row(
			modifier = Modifier
				.fillMaxSize()
				.padding(horizontal = 16.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Icon(
				imageVector = Icons.Default.Search,
				contentDescription = "Search Icon",
				tint = Color.Gray // Icono gris
			)
			Spacer(modifier = Modifier.width(8.dp))
			// Campo de texto básico para personalizar el estilo sin el marco de OutlinedTextField
			BasicTextField(
				value = searchText,
				onValueChange = onSearchTextChange,
				textStyle = TextStyle(
					color = Color.Gray, // Texto gris
					fontSize = 18.sp
				),
				modifier = Modifier.fillMaxWidth(),
				decorationBox = { innerTextField ->
					if (searchText.isEmpty()) {
						Text(
							text = "Search...", // Placeholder "Search..."
							style = TextStyle(
								color = Color.Gray,
								fontSize = 18.sp
							)
						)
					}
					innerTextField()
				}
			)
		}
	}
}

// Componente para cada ítem de la lista de búsqueda
@Composable
fun SearchItem(hero: SuperHero, onClick: (Long) -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.clickable { onClick(hero.id) }
			.padding(vertical = 12.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		// Imagen circular del héroe
		AsyncImage(
			model = hero.image,
			contentDescription = hero.name,
			modifier = Modifier
				.size(50.dp)
				.clip(CircleShape),
			contentScale = ContentScale.Crop
		)
		Spacer(modifier = Modifier.width(16.dp))
		// Nombre del héroe
		Text(
			text = hero.name,
			style = MaterialTheme.typography.titleMedium.copy(
				fontWeight = FontWeight.Bold,
				color = Color(0xFF2D3135)
			)
		)
	}
}