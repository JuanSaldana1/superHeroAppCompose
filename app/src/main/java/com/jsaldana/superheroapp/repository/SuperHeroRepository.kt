package com.jsaldana.superheroapp.repository

import com.jsaldana.superheroapp.data.model.SuperHero
import com.jsaldana.superheroapp.service.SuperHeroAPIService

class SuperHeroRepository(
	private val apiService: SuperHeroAPIService
) {

	/**
	 * Obtiene una lista paginada de superhéroes.
	 * @param offset Punto de inicio en la base de datos (ej. 0 para empezar desde el inicio).
	 * @param limit Cantidad de héroes a recuperar (ej. 5 por cada carga).
	 */
	suspend fun getAllSuperheroes(offset: Int, limit: Int): Result<List<SuperHero>> {
		return try {
			// Pasamos los parámetros al servicio de API
			val response = apiService.getAllSuperheroes(offset = offset, limit = limit)
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