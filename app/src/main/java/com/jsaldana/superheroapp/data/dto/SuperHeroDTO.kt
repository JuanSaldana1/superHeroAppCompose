package com.jsaldana.superheroapp.data.dto

import com.jsaldana.superheroapp.data.model.SuperHero
import kotlinx.serialization.Serializable

@Serializable
data class SuperHeroDTO(
	val id: Long,
	val name: String,
	val slug: String,
	val powerstats: SuperHeroPowerStatDTO,
	val appearance: SuperHeroAppearanceDTO,
	val biography: SuperHeroBiographyDTO,
	val work: SuperHeroWorkDTO,
	val connections: SuperHeroConnectionDTO,
	val images: SuperHeroImageDTO,
) {
	fun toModel() = SuperHero(
		id = id,
		name = name,
		image = images.md,
		work = work.occupation
	)
}
