package com.example.marvelsuperheroes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelsuperheroes.data.HeroesRepository
import com.example.marvelsuperheroes.data.HeroesRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HeroViewModel(
    heroId: String,
    private val heroesRepository: HeroesRepository = HeroesRepositoryImpl(),
) : ViewModel() {

    private val mutableStateFlow: MutableStateFlow<HeroState> = MutableStateFlow(HeroState.Loading)
    val stateFlow: StateFlow<HeroState> = mutableStateFlow

    init {
        viewModelScope.launch {
            try {
                val hero = heroesRepository.getHeroById(heroId)
                mutableStateFlow.value = HeroState.Success(hero)
            } catch (ex: Exception) {
                mutableStateFlow.value = HeroState.Error
            }
        }
    }
}