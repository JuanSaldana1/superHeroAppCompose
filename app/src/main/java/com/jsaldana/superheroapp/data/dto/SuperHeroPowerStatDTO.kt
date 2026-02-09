package com.jsaldana.superheroapp.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class SuperHeroPowerStatDTO(
	val intelligence: Long,
	val strength: Long,
	val speed: Long,
	val durability: Long,
	val power: Long,
	val combat: Long,
)
