package com.jsaldana.superheroapp.modules

import com.jsaldana.superheroapp.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModule = module {
	single(named("privateKey")) { BuildConfig.API_KEY }
}