package com.jsaldana.superheroapp


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsaldana.superheroapp.data.model.SuperHero
import com.jsaldana.superheroapp.repository.SuperHeroRepository
import kotlinx.coroutines.launch

class MainViewModel(
	private val repository: SuperHeroRepository,
) : ViewModel() {

	var state by mutableStateOf<SuperheroState>(SuperheroState.Idle)
		private set

	fun getAllHeroes() {
		viewModelScope.launch {
			state = SuperheroState.Loading
			repository.getAllSuperheroes()
				.onSuccess { list ->
					state = SuperheroState.Success(list) // Pasamos la lista completa
				}
				.onFailure { error ->
					state = SuperheroState.Error(error.message ?: "Error desconocido")
				}
		}
	}
}

sealed class SuperheroState {
	object Idle : SuperheroState()
	object Loading : SuperheroState()
	data class Success(val heroes: List<SuperHero>) : SuperheroState() // Cambio aquí: List
	data class Error(val message: String) : SuperheroState()
}