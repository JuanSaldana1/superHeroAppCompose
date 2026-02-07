package com.jsaldana.superheroapp

import android.app.Application
import com.jsaldana.superheroapp.modules.appModule
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
				//dataModule,
				//viewModelModule
			)
		}
	}
}