package com.jsaldana.superheroapp.repository

import com.jsaldana.superheroapp.service.SuperHeroAPIService

class SuperHeroRepository(
	private val apiService: SuperHeroAPIService
) {
	suspend fun getSuperHeroById(id: String): Result<Any> =
		try {
			Result.success(apiService.getSuperHeroById(id))
		} catch (t: Throwable) {
			Result.failure(t)
		}
}