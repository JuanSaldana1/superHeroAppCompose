package com.jsaldana.superheroapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SuperHero(
	val id: Long,
	val name: String,
	//val powerstats: PowerStatsDTO,
)
