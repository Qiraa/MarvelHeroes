package com.example.marvelsuperheroes.presentation

import com.example.marvelsuperheroes.data.Superhero

sealed class MainState {

    data object Loading : MainState()

    data class Success(
        val superheroes: List<Superhero>,
    ) : MainState()

    data object Error : MainState()
}