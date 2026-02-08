package com.jsaldana.superheroapp.modules

import com.jsaldana.superheroapp.service.SuperHeroAPIService
import com.jsaldana.superheroapp.service.SuperHeroServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType.Application.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File

private const val BASE_URL = "https://superheroapi.com/api/"

private const val TIME_OUT = 6000

val networkModule = module {

	single { KtorClientProvider.create(androidContext().cacheDir, isDebug = true) }
	single { "https://superheroapi.com/api/" }
	//single<SuperHeroAPIService> { SuperHeroServiceImpl(get(), get()) }
	// Ktor
	factory<SuperHeroAPIService> { SuperHeroServiceImpl(get()) }

	/*single {
		HttpClient(Android) {
			install(JsonFeature)
			{
				KotlinxSerializer(Json {
					prettyPrint = true
					isLenient = true
					ignoreUnknownKeys = true
				})

				engine {
					connectTimeout = TIME_OUT
					socketTimeout = TIME_OUT
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

	}*/
}

object KtorClientProvider {
	fun create(cacheDir: File, isDebug: Boolean = true): HttpClient {
		val okHttpClient = OkHttpClient.Builder()
			.cache(Cache(File(cacheDir, "http_cache"), 10L * 1024 * 1024)) // 10 MB cache
			.addInterceptor(Interceptor { chain ->
				val request = chain.request().newBuilder()
					.addHeader("Authorization", "Bearer token_here")
					.build()
				chain.proceed(request)
			})
			.build()

		return HttpClient(OkHttp) {
			engine { preconfigured = okHttpClient }

			/*install(ContentNegotiation) {
				json(Json {
					ignoreUnknownKeys = true
					isLenient = true
					explicitNulls = false
				})
			}*/

			install(HttpTimeout) {
				requestTimeoutMillis = 30_000
				connectTimeoutMillis = 15_000
				socketTimeoutMillis = 30_000
			}


			install(Logging) {
				logger = Logger.DEFAULT
				level = if (isDebug) LogLevel.BODY else LogLevel.NONE
			}
		}
	}
}