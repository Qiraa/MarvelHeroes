package com.example.marvelsuperheroes.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.marvelsuperheroes.R
import com.example.marvelsuperheroes.presentation.HeroViewModel

@Composable
fun HeroScreen(
    modifier: Modifier = Modifier,
    heroId: Int,
) {
    val viewModel = viewModel<HeroViewModel>()
    val hero = viewModel.getHeroById(heroId)

    if (hero == null) {
        Box(modifier = modifier.fillMaxSize()) {
            Text(stringResource(id = R.string.hero_not_found))
        }
    } else {
        HeroScreenContent(
            imageUrl = hero.imageUrl, 
            name = stringResource(id = hero.nameId), 
            description = stringResource(id = hero.descriptionId),
        )
    }
}

@Composable
fun HeroScreenContent(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    description: String,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier.padding(bottom = 32.dp, start = 16.dp),
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Preview
@Composable
fun HeroScreenPreview() {
    HeroScreenContent(
        imageUrl = "https://i.pinimg.com/originals/0e/a9/b4/0ea9b41396ca6ebdeba4848c31f6e030.jpg",
        name = "Hero",
        description = "Cool hero!"
    )
}
