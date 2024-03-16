package com.example.marvelsuperheroes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.marvelsuperheroes.R

class MainViewModel : ViewModel() {
    fun getMainItems(): List<Superhero> {
        return listOf(
            Superhero(
                imageUrl = "https://i.pinimg.com/736x/24/54/00/245400a91205ce47b880302a5729d0bd.jpg",
                nameId = R.string.deadpool,
                descriptionId = R.string.deadpoolDescription,
            ),
            Superhero(
                imageUrl = "https://i.pinimg.com/originals/0e/a9/b4/0ea9b41396ca6ebdeba4848c31f6e030.jpg",
                nameId = R.string.ironMan,
                descriptionId = R.string.ironManDescription,
            ),
            Superhero(
                imageUrl = "https://i.insider.com/51eea7f46bb3f7326e00001c?width=1200",
                nameId = R.string.spiderMan,
                descriptionId = R.string.spiderManDescription,
            )
        )
    }
}
