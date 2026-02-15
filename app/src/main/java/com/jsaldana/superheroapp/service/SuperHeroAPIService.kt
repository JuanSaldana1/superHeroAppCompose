package com.jsaldana.superheroapp.service

import com.jsaldana.superheroapp.data.dto.SuperHeroDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

interface SuperHeroAPIService {
	// Actualizamos la firma para aceptar los parámetros
	suspend fun getAllSuperheroes(offset: Int, limit: Int): List<SuperHeroDTO>
	suspend fun getSuperHeroById(id: Int): SuperHeroDTO
}

class SuperHeroServiceImpl(
	private val client: HttpClient,
) : SuperHeroAPIService {
	private val BASE_URL = "https://akabab.github.io/superhero-api/api/"

	// Cacheamos la lista en memoria para no descargar el JSON de 1MB en cada scroll
	private var cachedHeroes: List<SuperHeroDTO>? = null

	override suspend fun getAllSuperheroes(offset: Int, limit: Int): List<SuperHeroDTO> {
		// 1. Si no tenemos los héroes, los descargamos todos una sola vez
		val allHeroes = cachedHeroes ?: client.get(BASE_URL) {
			url { appendPathSegments("all.json") }
		}.body<List<SuperHeroDTO>>().also {
			cachedHeroes = it
		}

		// 2. Calculamos el final del rango de forma segura
		val toIndex = (offset + limit).coerceAtMost(allHeroes.size)

		// 3. Si el offset ya supera el tamaño, devolvemos lista vacía
		if (offset >= allHeroes.size) return emptyList()

		// 4. Devolvemos solo el segmento (sublista) solicitado
		return allHeroes.subList(offset, toIndex)
	}

	override suspend fun getSuperHeroById(id: Int): SuperHeroDTO =
		client.get(BASE_URL) {
			url {
				appendPathSegments("id", "$id.json") // Corregido: Ktor maneja mejor los segmentos separados
			}
		}.body()
}