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
		}
	}
}

sealed class SuperheroState {
	object Idle : SuperheroState()
	object Loading : SuperheroState()
	data class Success(val hero: SuperHero) : SuperheroState()
	data class Error(val message: String) : SuperheroState()
}