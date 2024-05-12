package com.example.marvelsuperheroes.data

interface HeroesRepository {
    suspend fun getAllHeroes(): List<Superhero>

    suspend fun getHeroById(id: String): Superhero
}