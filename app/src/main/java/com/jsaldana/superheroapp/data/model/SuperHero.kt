package com.jsaldana.superheroapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SuperHero(
	val id: Long,
	val name: String,
	val image: String,
	val work: String
	//val powerstats: PowerStatsDTO,
)
