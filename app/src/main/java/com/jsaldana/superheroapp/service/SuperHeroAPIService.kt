package com.jsaldana.superheroapp.service

import com.jsaldana.superheroapp.data.dto.SuperheroResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface SuperHeroAPIService {
	suspend fun getSuperHeroById(id: String): SuperheroResponse
}

class SuperHeroServiceImpl(
	private val client: HttpClient,
) : SuperHeroAPIService {
	private val BASE_URL = "https://superheroapi.com/api/"


	override suspend fun getSuperHeroById(id: String): SuperheroResponse =
		client.get("$BASE_URL/$id").body()

}