package com.example.marvelsuperheroes.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.marvelsuperheroes.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val superheroes = listOf(
        Superhero(
            imageUrl = "https://i.pinimg.com/736x/24/54/00/245400a91205ce47b880302a5729d0bd.jpg",
            name = "Deadpool",
            description = "",
        ),
        Superhero(
            imageUrl = "https://i.pinimg.com/originals/0e/a9/b4/0ea9b41396ca6ebdeba4848c31f6e030.jpg",
            name = "Iron Man",
            description = "",
        ),
        Superhero(
            imageUrl = "https://i.insider.com/51eea7f46bb3f7326e00001c?width=1200",
            name = "Spider Man",
            description = "",
        )
    )

    Column(
        modifier = modifier
            .paint(
                painter = painterResource(id = R.drawable.main_background),
                contentScale = ContentScale.Crop,
            )
            .systemBarsPadding()
            .padding(top = 16.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.marvel_studios),
            modifier = Modifier.width(148.dp),
        )
        Text(
            text = stringResource(id = R.string.choose_hero),
            style = MaterialTheme.typography.headlineLarge,
        )
        val state = rememberLazyListState()

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(24.dp),
            state = state,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = state),
        ) {
            items(superheroes) { hero ->
                MainCard(
                    imageUrl = hero.imageUrl,
                    name = hero.name,
                    modifier = Modifier.fillParentMaxSize()
                )
            }
        }
    }
}