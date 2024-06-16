package com.example.marvelsuperheroes.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.marvelsuperheroes.R
import com.example.marvelsuperheroes.presentation.HeroState
import com.example.marvelsuperheroes.presentation.HeroViewModel

@Composable
fun HeroScreen(
    modifier: Modifier = Modifier,
    heroId: String,
) {
    val viewModel = hiltViewModel<HeroViewModel, HeroViewModel.Factory> { factory ->
        factory.create(heroId)
    }
    val state by viewModel.stateFlow.collectAsState()
    when (val currentState = state) {
        is HeroState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is HeroState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(id = R.string.error))
            }
        }
        is HeroState.Success -> {
            HeroScreenContent(
                modifier = modifier,
                imageUrl = currentState.superhero.imageUrl,
                name = currentState.superhero.name,
                description = currentState.superhero.description,
            )
        }
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
                    .copy(color = Color.White)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.headlineMedium
                    .copy(color = Color.White)
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
