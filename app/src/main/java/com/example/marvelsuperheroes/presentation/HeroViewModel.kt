package com.example.marvelsuperheroes.presentation

import androidx.lifecycle.ViewModel
import com.example.marvelsuperheroes.data.HeroesRepositoryImpl
import com.example.marvelsuperheroes.data.HeroesRepository
import com.example.marvelsuperheroes.data.Superhero

class HeroViewModel(
    private val heroesRepository: HeroesRepository = HeroesRepositoryImpl(),
) : ViewModel() {
    fun getHeroById(id: Int): Superhero? {
        return heroesRepository.getHeroById(id)
    }
}