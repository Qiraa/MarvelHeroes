package com.example.marvelsuperheroes.data

interface HeroesRepository {
    fun getAllHeroes(): List<Superhero>

    fun getHeroById(id: Int): Superhero?
}