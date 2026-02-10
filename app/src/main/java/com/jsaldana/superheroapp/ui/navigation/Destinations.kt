package com.jsaldana.superheroapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlaylistAddCircle
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
	val route: String,
	val label: String,
	val icon: ImageVector,
	val contentDescription: String
) {
	DISCOVER("discover", "Discover", Icons.Default.MusicNote, "Discover"),
	SEARCH("search", "Search", Icons.Default.Album, "Search"),
	PROFILE("profile", "Profile", Icons.Default.PlaylistAddCircle, "Profile"),
}