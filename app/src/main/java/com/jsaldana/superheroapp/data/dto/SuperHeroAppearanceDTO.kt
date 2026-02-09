package com.jsaldana.superheroapp.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class SuperHeroAppearanceDTO(
	val gender: String,
	val race: String,
	val height: List<String>,
	val weight: List<String>,
	val eyeColor: String,
	val hairColor: String,
)
