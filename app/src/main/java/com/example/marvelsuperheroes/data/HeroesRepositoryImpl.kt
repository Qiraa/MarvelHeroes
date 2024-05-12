package com.example.marvelsuperheroes.data

import com.example.marvelsuperheroes.R

class HeroesRepositoryImpl : HeroesRepository {
    override fun getAllHeroes(): List<Superhero> {
        return listOf(
            Superhero(
                id = 0,
                imageUrl = "https://i.pinimg.com/736x/24/54/00/245400a91205ce47b880302a5729d0bd.jpg",
                nameId = R.string.deadpool,
                descriptionId = R.string.deadpoolDescription,
            ),
            Superhero(
                id = 1,
                imageUrl = "https://i.pinimg.com/originals/0e/a9/b4/0ea9b41396ca6ebdeba4848c31f6e030.jpg",
                nameId = R.string.ironMan,
                descriptionId = R.string.ironManDescription,
            ),
            Superhero(
                id = 2,
                imageUrl = "https://i.insider.com/51eea7f46bb3f7326e00001c?width=1200",
                nameId = R.string.spiderMan,
                descriptionId = R.string.spiderManDescription,
            )
        )
    }

    override fun getHeroById(id: Int): Superhero? {
        return getAllHeroes().find { hero ->
            id == hero.id
        }
    }
}
