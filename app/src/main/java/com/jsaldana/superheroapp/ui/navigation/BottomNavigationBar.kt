package com.jsaldana.superheroapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun CustomBottomBar(navController: NavHostController) { // <-- Recibimos el navController
	// Observamos la ruta actual para que los iconos se iluminen correctamente
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route

	NavigationBar(
		containerColor = Color(0xFF1A1C1E),
		tonalElevation = 0.dp
	) {
		// ITEM: DISCOVER
		NavigationBarItem(
			selected = currentRoute == Destination.DISCOVER.route,
			onClick = {
				navController.navigate(Destination.DISCOVER.route) {
					popUpTo(navController.graph.startDestinationId) { saveState = true }
					launchSingleTop = true
					restoreState = true
				}
			},
			icon = { Icon(Icons.Default.Layers, contentDescription = null) },
			label = { Text("Discover") }
		)

		// ITEM: SEARCH (Aquí es donde ocurre la magia que pediste)
		NavigationBarItem(
			selected = currentRoute == Destination.SEARCH.route,
			onClick = {
				navController.navigate(Destination.SEARCH.route) {
					popUpTo(navController.graph.startDestinationId) { saveState = true }
					launchSingleTop = true
					restoreState = true
				}
			},
			icon = { Icon(Icons.Default.Search, contentDescription = null) },
			label = { Text("Search") }
		)

		// ITEM: PROFILE
		NavigationBarItem(
			selected = currentRoute == Destination.PROFILE.route,
			onClick = { /* Navegar a perfil si existe la ruta */ },
			icon = { Icon(Icons.Default.Person, contentDescription = null) },
			label = { Text("Profile") }
		)
	}
}
