package com.jsaldana.superheroapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jsaldana.superheroapp.MainViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

// Definimos las rutas como objetos/clases serializables
@Serializable
object HeroListRoute

@Serializable
data class HeroDetailRoute(val id: Long)

@Composable
fun SuperheroNavigation(viewModel: MainViewModel = koinViewModel()) {
	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = HeroListRoute) {

		// Definición de pantalla usando el TIPO, no un String
		/*composable<HeroListRoute> {
			SuperheroListScreen(
				onHeroClick = { id -> navController.navigate(HeroDetailRoute(id)) },
				state = {},
				onLoad = {}
			)
		}

		// Recuperación de argumentos automática
		composable<HeroDetailRoute> { backStackEntry ->
			val route: HeroDetailRoute = backStackEntry.toRoute()
			// Ya no necesitas buscar el ID manualmente ni hacer parseo de Strings
			HeroDetailScreen(heroId = route.id)
		}*/
	}
}