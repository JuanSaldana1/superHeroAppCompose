package com.jsaldana.superheroapp.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class SuperHeroConnectionDTO(
	val groupAffiliation: String,
	val relatives: String,
)
