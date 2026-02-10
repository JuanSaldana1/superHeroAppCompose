package com.jsaldana.superheroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.jsaldana.superheroapp.ui.navigation.AppNavHost
import com.jsaldana.superheroapp.ui.navigation.Destination
import com.jsaldana.superheroapp.ui.theme.SuperHeroAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
	val viewModel: MainViewModel by viewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SuperHeroAppTheme {
				AppNavHost(
					navController = rememberNavController(),
					startDestination = Destination.DISCOVER,
					viewModel = viewModel
				)
			}
		}
	}
}
