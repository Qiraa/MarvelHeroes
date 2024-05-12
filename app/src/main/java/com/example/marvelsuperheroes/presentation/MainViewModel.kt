package com.example.marvelsuperheroes.presentation

import androidx.lifecycle.ViewModel
import com.example.marvelsuperheroes.data.HeroesRepositoryImpl
import com.example.marvelsuperheroes.data.HeroesRepository
import com.example.marvelsuperheroes.data.Superhero

class MainViewModel(
    private val heroesRepository: HeroesRepository = HeroesRepositoryImpl(),
) : ViewModel() {
    fun getMainItems(): List<Superhero> {
        return heroesRepository.getAllHeroes()
    }
}
