package com.jsaldana.superheroapp.modules

import android.util.Log
import com.jsaldana.superheroapp.service.SuperHeroServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
	single {
		HttpClient(OkHttp) {
			install(ContentNegotiation) {
				json(Json {
					explicitNulls = false
					ignoreUnknownKeys = true
					isLenient = true
					prettyPrint = true
				})
			}

			install(HttpTimeout) {
				requestTimeoutMillis = 30_000
				connectTimeoutMillis = 15_000
				socketTimeoutMillis = 30_000
			}

			//Logging
			install(Logging) {
				logger = object : Logger {
					override fun log(message: String) {
						Log.d("HttpLogging:", message)
					}
				}
			}

			//Http Response
			install(ResponseObserver) {
				onResponse { response ->
					Log.d("HTTP status:", "${response.status.value}")
				}
			}

			// Headers
			install(DefaultRequest) {
				header(HttpHeaders.ContentType, ContentType.Application.Json)
			}
		}
	}

	// Ktor
	single { SuperHeroServiceImpl(get()) }
}