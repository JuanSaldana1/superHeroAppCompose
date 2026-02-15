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

	private var currentOffset = 0
	private val pageSize = 5
	var isPaginating by mutableStateOf(false)
		private set

	// Estado independiente para el detalle
	var detailState by mutableStateOf<SuperheroState>(SuperheroState.Idle)
		private set

	var searchText by mutableStateOf("")
		private set

	val filteredHeroes: List<SuperHero>
		get() {
			val currentState = state
			if (currentState is SuperheroState.Success) {
				return if (searchText.isBlank()) {
					currentState.heroes // Si no hay texto, muestra todos
				} else {
					currentState.heroes.filter {
						it.name.contains(searchText, ignoreCase = true) // Filtra por nombre
					}
				}
			}
			return emptyList() // Retorna lista vacía si los datos no están cargados
		}

	fun getAllHeroes() {
		viewModelScope.launch {
			state = SuperheroState.Loading
			repository.getAllSuperheroes(1000, 1000)
				.onSuccess { list ->
					state = SuperheroState.Success(list) // Pasamos la lista completa
				}
				.onFailure { error ->
					state = SuperheroState.Error(error.message ?: "Error desconocido")
				}
		}
	}

	// Función para cargar los siguientes 5
	fun loadMoreHeroes() {
		val currentState = state
		if (isPaginating || currentState !is SuperheroState.Success) return

		isPaginating = true
		viewModelScope.launch {
			repository.getAllSuperheroes(offset = currentOffset, limit = pageSize)
				.onSuccess { newList ->
					if (newList.isNotEmpty()) {
						// Concatenamos la lista actual con la nueva
						val updatedList = currentState.heroes + newList
						state = SuperheroState.Success(updatedList)
						currentOffset += newList.size
					}
					isPaginating = false
				}
				.onFailure {
					isPaginating = false
					// Opcional: manejar error de paginación silenciosamente
				}
		}
	}

	fun getHeroByID(id: Int) {
		viewModelScope.launch {
			detailState = SuperheroState.Loading
			repository.getSuperHeroById(id)
				.onSuccess { hero ->
					detailState = SuperheroState.Success(listOf(hero))
				}
				.onFailure { error ->
					detailState = SuperheroState.Error(error.message ?: "Error desconocido")
				}
		}
	}

	fun onSearchTextChange(text: String) {
		searchText = text
	}

	// Limpia el estado del detalle al salir para evitar ver el héroe anterior al entrar en uno nuevo
	fun resetDetailState() {
		detailState = SuperheroState.Idle
	}
}

sealed class SuperheroState {
	object Idle : SuperheroState()
	object Loading : SuperheroState()
	data class Success(val heroes: List<SuperHero>) : SuperheroState() // Cambio aquí: List
	data class Error(val message: String) : SuperheroState()
}