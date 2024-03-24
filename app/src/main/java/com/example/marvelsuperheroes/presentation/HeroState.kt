package com.example.marvelsuperheroes.presentation

import com.example.marvelsuperheroes.data.Superhero

sealed class HeroState {
    data object Loading : HeroState()
    data class Success(
        val superhero: Superhero,
    ) : HeroState()

    data object Error : HeroState()
}