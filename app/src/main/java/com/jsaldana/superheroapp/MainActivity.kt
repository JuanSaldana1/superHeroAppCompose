package com.jsaldana.superheroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jsaldana.superheroapp.ui.theme.SuperHeroAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			SuperHeroAppTheme {
				Scaffold { innerPadding ->
					Greeting(modifier = Modifier.padding(innerPadding))
				}
			}
		}
	}
}

@Preview
@Composable
fun Greeting(modifier: Modifier = Modifier, viewModel: MainViewModel = koinViewModel()) {
	val heroResponse = viewModel.getAllHeroes().toString()

	Text(
		text = heroResponse,
		modifier = modifier
	)
}