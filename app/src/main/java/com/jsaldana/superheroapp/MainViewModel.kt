package com.jsaldana.superheroapp

import androidx.lifecycle.ViewModel
import com.jsaldana.superheroapp.repository.SuperHeroRepository

class MainViewModel(
	private val repository: SuperHeroRepository,
) : ViewModel() {
}