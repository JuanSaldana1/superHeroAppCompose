package com.jsaldana.superheroapp.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class SuperHeroBiographyDTO(
	val fullName: String,
	val alterEgos: String,
	val aliases: List<String>,
	val placeOfBirth: String,
	val firstAppearance: String,
	val publisher: String?,
	val alignment: String,
)
