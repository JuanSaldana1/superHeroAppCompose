package com.jsaldana.superheroapp.modules

import org.koin.dsl.module


val appModule = module {
	//single { BuildConfig.BASE_URL }
}


class AppModule {

	fun providePrivateKey(): String {
		return "private_key"
	}
}