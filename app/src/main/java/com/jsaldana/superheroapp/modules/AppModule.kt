package com.jsaldana.superheroapp.modules

import com.jsaldana.superheroapp.BuildConfig
import com.jsaldana.superheroapp.MainViewModel
import com.jsaldana.superheroapp.repository.SuperHeroRepository
import com.jsaldana.superheroapp.service.SuperHeroAPIService
import com.jsaldana.superheroapp.service.SuperHeroServiceImpl
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModule = module {
	single(named("privateKey")) { BuildConfig.API_KEY }
	single<SuperHeroAPIService> { SuperHeroServiceImpl(get()) }
	single { SuperHeroRepository(get()) }
	viewModel { MainViewModel(get()) }
}