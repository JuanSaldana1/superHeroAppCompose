package com.jsaldana.superheroapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.TrendingUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jsaldana.superheroapp.SuperheroState
import com.jsaldana.superheroapp.ui.SampleData
import com.jsaldana.superheroapp.ui.navigation.CustomBottomBar
import com.jsaldana.superheroapp.ui.screens.components.HeroCarousel
import com.jsaldana.superheroapp.ui.screens.components.MainHeader
import com.jsaldana.superheroapp.ui.screens.components.SectionHeader
import com.jsaldana.superheroapp.ui.theme.SuperHeroAppTheme

@Composable
fun SuperheroListScreen(
	navController: NavHostController,
	state: SuperheroState,
	isPaginating: Boolean,
	onLoad: () -> Unit,
	onLoadMore: () -> Unit,
	onHeroClick: (Long) -> Unit,
) {
	LaunchedEffect(Unit) { onLoad() }

	Scaffold(
		containerColor = Color(0xFFF5F3E9), // El color crema de la imagen
		bottomBar = { CustomBottomBar(navController) }
	) { padding ->
		Box(modifier = Modifier.padding(padding)) {
			Column(modifier = Modifier.fillMaxSize()) {
				MainHeader() // El logo "hero" arriba a la derecha

				when (state) {
					is SuperheroState.Loading -> {
						CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
					}

					is SuperheroState.Success -> {
						LazyColumn(modifier = Modifier.fillMaxSize()) {
							// SECCIÓN POPULAR
							item {
								SectionHeader(
									title = "Popular",
									icon = Icons.AutoMirrored.Rounded.TrendingUp
								)
								HeroCarousel(state.heroes.take(5), onHeroClick)
							}

							// 2. LISTADO GENERAL (Aquí ocurre la magia)
							item { SectionHeader(title = "All Heroes", emoji = "🦸‍♂️") }

							items(state.heroes.size) { index ->
								val hero = state.heroes[index]
								// Aquí iría tu componente de fila de héroe (HeroRow o similar)
								Text(
									text = "${index + 1}. ${hero.name}",
									modifier = Modifier.padding(16.dp)
								)
							}

							// 3. INDICADOR DE CARGA AL FINAL
							item {
								if (!isPaginating) {
									// Cuando este bloque entra en composición, pedimos más datos
									LaunchedEffect(Unit) {
										onLoadMore()
									}
								} else {
									Box(
										modifier = Modifier
											.fillMaxWidth()
											.padding(16.dp),
										contentAlignment = Alignment.Center
									) {
										CircularProgressIndicator()
									}
								}
							}

							item { Spacer(modifier = Modifier.height(80.dp)) }

							// SECCIÓN VILLANOS
							item {
								SectionHeader(
									title = "Villians",
									emoji = "😈"
								)
								HeroCarousel(state.heroes.reversed().take(5), onHeroClick)
							}

							// Espacio extra al final para que el FAB no tape nada
							item { Spacer(modifier = Modifier.height(80.dp)) }
						}
					}

					is SuperheroState.Error -> {
						Text("Error: ${state.message}", Modifier.padding(20.dp))
					}

					else -> {}
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun SuperheroListScreenSuccessPreview() {
	SuperHeroAppTheme {
		SuperheroListScreen(
			navController = NavHostController(LocalContext.current),
			state = SuperheroState.Success(SampleData.heroes),
			isPaginating = false,
			onLoad = {},
			onLoadMore = {},
			onHeroClick = {},
		)
	}
}

@Preview(showBackground = true)
@Composable
fun SuperheroListScreenLoadingPreview() {
	SuperHeroAppTheme {
		SuperheroListScreen(
			navController = NavHostController(LocalContext.current),
			state = SuperheroState.Loading,
			isPaginating = true,
			onLoad = {},
			onLoadMore = {},
			onHeroClick = {},
		)
	}
}

@Preview(showBackground = true)
@Composable
fun SuperheroListScreenErrorPreview() {
	SuperHeroAppTheme {
		SuperheroListScreen(
			navController = NavHostController(LocalContext.current),
			state = SuperheroState.Error("Something went wrong"),
			isPaginating = false,
			onLoad = {},
			onLoadMore = {},
			onHeroClick = {},
		)
	}
}