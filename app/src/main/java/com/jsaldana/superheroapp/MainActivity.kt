package com.jsaldana.superheroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

	// Ejecuta la carga solo la primera vez que se muestra el componente
	LaunchedEffect(Unit) {
		viewModel.getAllHeroes()
	}

	val state = viewModel.state

	// Gestionamos qué mostrar según el estado
	when (state) {
		is SuperheroState.Loading -> {
			Text(text = "Cargando héroes...", modifier = modifier)
		}

		is SuperheroState.Success -> {
			// Convertimos la lista a un String para el Text
			val content = state.heroes.joinToString("\n") { it.name }
			Text(text = content, modifier = modifier)
		}

		is SuperheroState.Error -> {
			Text(text = "Error: ${state.message}", modifier = modifier)
		}

		else -> { /* Idle */
		}
	}
}