package com.jsaldana.superheroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jsaldana.superheroapp.ui.SuperheroNavigation
import com.jsaldana.superheroapp.ui.theme.SuperHeroAppTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SuperHeroAppTheme {
				SuperheroNavigation()
			}
		}
	}
}

