package com.jsaldana.superheroapp.repository

import com.jsaldana.superheroapp.data.model.SuperHero
import com.jsaldana.superheroapp.service.SuperHeroAPIService

class SuperHeroRepository(
	private val apiService: SuperHeroAPIService
) {

	suspend fun getAllSuperheroes(): Result<List<SuperHero>> {
		return try {
			val response = apiService.getAllSuperheroes()
			Result.success(response.map { it.toModel() })
		} catch (e: Exception) {
			Result.failure(e)
		}
	}

	suspend fun getSuperHeroById(id: Int): Result<SuperHero> {
		return try {
			val response = apiService.getSuperHeroById(id)
			Result.success(response.toModel())
		} catch (e: Exception) {
			Result.failure(e)
		}
	}
}