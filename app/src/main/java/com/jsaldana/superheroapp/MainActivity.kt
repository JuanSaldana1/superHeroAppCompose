package com.jsaldana.superheroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
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

	// Gestionamos qué mostrar según el estado
	when (val state = viewModel.state) {
		is SuperheroState.Loading -> {
			Text(text = "Cargando héroes...", modifier = modifier)
		}

		is SuperheroState.Success -> {
			val heroList = state.heroes
			LazyColumn(
				modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
				verticalArrangement = Arrangement.SpaceBetween
			) {
				items(heroList) { hero ->
					Column {
						AsyncImage(
							model = ImageRequest.Builder(LocalContext.current)
								.data(hero.image)
								.crossfade(true)
								.build(),
							modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
							contentDescription = hero.name
						)
						Text(text = hero.name, modifier = modifier)
					}

				}
			}
		}

		is SuperheroState.Error -> {
			Text(text = "Error: ${state.message}", modifier = modifier)
		}

		else -> { /* Idle */
		}
	}
}