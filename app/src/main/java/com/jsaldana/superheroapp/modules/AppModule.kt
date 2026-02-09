package com.jsaldana.superheroapp.modules

import com.jsaldana.superheroapp.MainViewModel
import com.jsaldana.superheroapp.repository.SuperHeroRepository
import com.jsaldana.superheroapp.service.SuperHeroAPIService
import com.jsaldana.superheroapp.service.SuperHeroServiceImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
	single<SuperHeroAPIService> { SuperHeroServiceImpl(get()) }
	single { SuperHeroRepository(get()) }
	viewModel { MainViewModel(get()) }
}