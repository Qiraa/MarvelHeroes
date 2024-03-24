package com.example.marvelsuperheroes.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.marvelsuperheroes.R
import com.example.marvelsuperheroes.data.Superhero
import com.example.marvelsuperheroes.presentation.MainState
import com.example.marvelsuperheroes.presentation.MainViewModel
import com.example.marvelsuperheroes.ui.HeroScreen
import com.example.marvelsuperheroes.ui.components.MainCard

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val viewModel = viewModel<MainViewModel>()
    val state by viewModel.stateFlow.collectAsState()
    when (val currentState = state) {
        is MainState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is MainState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(id = R.string.error))
            }
        }
        is MainState.Success -> {
            MainContent(
                modifier = modifier,
                navController = navController,
                superheroes = currentState.superheroes,
            )
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun MainContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    superheroes: List<Superhero>,
) {
    val lazyListState = rememberLazyListState()
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

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(24.dp),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState),
        ) {
            items(superheroes) { hero ->
                MainCard(
                    imageUrl = hero.imageUrl,
                    name = hero.name,
                    modifier = Modifier.fillParentMaxSize(),
                    onClick = {
                        navController.navigateToHeroScreen(hero.id)
                    },
                )
            }
        }
    }
}

private fun NavController.navigateToHeroScreen(id: String) {
    navigate(HeroScreen.withHeroId(id))
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}