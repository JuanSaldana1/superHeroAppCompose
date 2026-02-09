package com.jsaldana.superheroapp.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class SuperHeroWorkDTO(
	val occupation: String,
	val base: String,
)
