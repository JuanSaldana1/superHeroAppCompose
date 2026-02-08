package com.jsaldana.superheroapp

import android.app.Application
import com.facebook.FacebookSdk
import com.jsaldana.superheroapp.modules.appModule
import com.jsaldana.superheroapp.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class SuperHeroApplication : Application() {
	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidLogger()
			androidContext(this@SuperHeroApplication)
			modules(
				appModule,
				networkModule,
				//dataModule,
				//viewModelModule
			)
		}
	}
}