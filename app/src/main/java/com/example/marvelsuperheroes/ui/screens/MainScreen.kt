package com.example.marvelsuperheroes.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.marvelsuperheroes.R
import com.example.marvelsuperheroes.presentation.MainViewModel
import com.example.marvelsuperheroes.ui.HeroScreen
import com.example.marvelsuperheroes.ui.components.MainCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val viewModel = viewModel<MainViewModel>()
    val lazyListState = rememberLazyListState()
    val items = viewModel.getMainItems()
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
            items(items) { hero ->
                MainCard(
                    imageUrl = hero.imageUrl,
                    name = stringResource(id = hero.nameId),
                    modifier = Modifier.fillParentMaxSize(),
                    onClick = {
                        navController.navigateToHeroScreen(hero.id)
                    },
                )
            }
        }
    }
}

private fun NavController.navigateToHeroScreen(id: Int) {
    navigate(HeroScreen.withHeroId(id))
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}