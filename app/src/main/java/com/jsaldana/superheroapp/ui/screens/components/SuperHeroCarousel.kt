package com.jsaldana.superheroapp.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.jsaldana.superheroapp.data.model.SuperHero

@Composable
fun HeroCarousel(heroes: List<SuperHero>, onHeroClick: (Long) -> Unit) {
	LazyRow(
		contentPadding = PaddingValues(horizontal = 16.dp),
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		items(heroes) { hero ->
			HeroCardLarge(hero, onHeroClick)
		}
	}
}