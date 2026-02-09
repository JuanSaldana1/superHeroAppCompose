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

	/*suspend fun getSuperHeroById(id: String): Result<SuperHero> {
		return try {
			val response = apiService.getSuperHeroById(id)
			if (response.response == "success") {
				Result.success(response.toModel()) // Usamos el mapper de la respuesta anterior
			} else {
				Result.failure(Exception(response.error))
			}
		} catch (e: Exception) {
			Result.failure(e)
		}
	}*/
}