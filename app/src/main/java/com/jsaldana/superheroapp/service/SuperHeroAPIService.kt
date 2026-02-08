package com.jsaldana.superheroapp.service

import com.jsaldana.superheroapp.data.model.SuperHero
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface SuperHeroAPIService {
	suspend fun getSuperHeroById(id: String): Any
}

class SuperHeroServiceImpl(
	private val client: HttpClient,
) : SuperHeroAPIService {
	private val BASE_URL = "https://superheroapi.com/api/"


	override suspend fun getSuperHeroById(id: String): Any =
		client.get("$BASE_URL/$id").toString()

}