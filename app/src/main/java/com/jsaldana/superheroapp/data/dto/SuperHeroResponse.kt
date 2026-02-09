package com.jsaldana.superheroapp.data.dto

import com.jsaldana.superheroapp.data.model.SuperHero
import kotlinx.serialization.Serializable

@Serializable
data class SuperheroResponse(
	val response: String,
	val id: String? = null,
	val name: String? = null,
	//val powerstats: PowerstatsDTO? = null,
	//val image: ImageDTO? = null,
	val error: String? = null
) {
	fun toModel() = SuperHero(
		id = id ?: "",
		name = name ?: ""
	)
}
