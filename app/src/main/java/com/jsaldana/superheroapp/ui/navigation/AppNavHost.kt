package com.jsaldana.superheroapp.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jsaldana.superheroapp.MainViewModel
import com.jsaldana.superheroapp.ui.screens.HeroDetailScreen
import com.jsaldana.superheroapp.ui.screens.SearchScreen
import com.jsaldana.superheroapp.ui.screens.home.SuperheroListScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
	navController: NavHostController,
	startDestination: Destination,
	viewModel: MainViewModel = koinViewModel()
) {
	NavHost(navController = navController, startDestination = startDestination.route) {
		Destination.entries.forEach { destination ->
			composable(destination.route) {
				when (destination) {
					Destination.DISCOVER -> SuperheroListScreen(
						navController = navController,
						state = viewModel.state,
						onLoad = { viewModel.getAllHeroes() },
						onHeroClick = { id -> navController.navigate("detail/$id") },
					)

					Destination.SEARCH -> SearchScreen(
						viewModel = viewModel,
						onHeroClick = { id ->
							// Al hacer clic en un resultado, navega al detalle
							navController.navigate("detail/$id")
						}
					)

					Destination.PROFILE -> {}
				}
			}
		}

		composable("detail/{heroId}") { backStackEntry ->
			// Extraemos el ID (como Int, que es lo que pide tu servicio)
			val heroId = backStackEntry.arguments?.getString("heroId")?.toIntOrNull() ?: 0

			HeroDetailScreen(
				heroId = heroId,
				viewModel = viewModel,
				onBack = {
					viewModel.resetDetailState() // Opcional: limpiar antes de volver
					navController.popBackStack()
				}
			)
		}
	}
}

@Preview()
// [START android_compose_components_navigationbarexample]
@Composable
fun NavigationBarExample(modifier: Modifier = Modifier) {
	val navController = rememberNavController()
	val startDestination = Destination.DISCOVER
	var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

	Scaffold(
		modifier = modifier,
		bottomBar = {
			NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
				Destination.entries.forEachIndexed { index, destination ->
					NavigationBarItem(
						selected = selectedDestination == index,
						onClick = {
							navController.navigate(route = destination.route)
							selectedDestination = index
						},
						icon = {
							Icon(
								destination.icon,
								contentDescription = destination.contentDescription
							)
						},
						label = { Text(destination.label) }
					)
				}
			}
		}
	) { contentPadding ->
		AppNavHost(navController, startDestination)
	}
}
// [END android_compose_components_navigationbarexample]