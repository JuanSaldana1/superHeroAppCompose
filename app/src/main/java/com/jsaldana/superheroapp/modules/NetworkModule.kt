package com.jsaldana.superheroapp.modules

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
	single(named("BASE_URL")) { "https://superheroapi.com/api/" }
	single { HttpClient(CIO)  }
}