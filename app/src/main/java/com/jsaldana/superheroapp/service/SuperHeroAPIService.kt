package com.jsaldana.superheroapp.service

import com.jsaldana.superheroapp.data.dto.SuperHeroDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

interface SuperHeroAPIService {
	suspend fun getAllSuperheroes(): List<SuperHeroDTO>
	//suspend fun getSuperHeroById(id: String): SuperheroResponse
}

class SuperHeroServiceImpl(
	private val client: HttpClient,
) : SuperHeroAPIService {
	private val BASE_URL = "https://akabab.github.io/superhero-api/api/"


	override suspend fun getAllSuperheroes(): List<SuperHeroDTO> =
		client.get(BASE_URL) {
			url {
				appendPathSegments("all.json")
			}
		}.body<List<SuperHeroDTO>>()

	/*override suspend fun getSuperHeroById(id: String): SuperheroResponse =
		client.get(BASE_URL) {
			url {
				appendPathSegments(id)
			}
		}.body()*/
}