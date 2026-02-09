package com.jsaldana.superheroapp

import androidx.lifecycle.ViewModel
import com.jsaldana.superheroapp.repository.SuperHeroRepository

class MainViewModel(
	private val repository: SuperHeroRepository,
) : ViewModel() {
	suspend fun getSuperHeroById(id: String): Result<Any> =
		repository.getSuperHeroById(id)
}