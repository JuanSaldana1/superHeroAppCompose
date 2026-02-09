package com.jsaldana.superheroapp.ui

import com.jsaldana.superheroapp.data.model.SuperHero

object SampleData {
	val heroes = listOf(
		SuperHero(
			1,
			"SUPERMAN",
			"https://www.superherodb.com/pictures2/portraits/10/100/791.jpg",
			"reporter",
			"Kryptonian",
			"Male"
		),
		SuperHero(
			2,
			"BATMAN",
			"https://www.superherodb.com/pictures2/portraits/10/100/639.jpg",
			"CEO",
			"Human",
			"Male"
		)
	)
}