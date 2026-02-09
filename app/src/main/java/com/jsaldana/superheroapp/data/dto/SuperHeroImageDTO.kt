package com.jsaldana.superheroapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SuperHeroImageDTO (
	val xs: String,
	val sm: String,
	val md: String,
	val lg: String,
)