package com.jsaldana.superheroapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.jsaldana.superheroapp.MainViewModel
import com.jsaldana.superheroapp.SuperheroState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SuperheroNavigation(viewModel: MainViewModel = koinViewModel()) {
	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = "list") {
		composable("list") {
			SuperheroListScreen(
				state = viewModel.state,
				onLoad = { viewModel.getAllHeroes() },
				onHeroClick = { id -> navController.navigate("detail/$id") }
			)
		}
		composable("detail/{heroId}") { backStackEntry ->
			val id = backStackEntry.arguments?.getString("heroId")?.toLongOrNull()
			val hero = (viewModel.state as? SuperheroState.Success)?.heroes?.find { it.id == id }
			hero?.let { HeroDetailScreen(it) { navController.popBackStack() } }
		}
	}
}