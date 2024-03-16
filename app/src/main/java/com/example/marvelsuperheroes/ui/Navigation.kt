package com.example.marvelsuperheroes.ui

import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelsuperheroes.ui.screens.HeroScreen
import com.example.marvelsuperheroes.ui.screens.MainScreen

const val IMAGE_URL = "imageUrl"
const val NAME = "name"
const val DESCRIPTION = "description"

@Composable
fun SetupNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main",
        enterTransition = { scaleIn() },
    ) {
        composable("main") {
            MainScreen(navController = navController)
        }
        composable(
            route = "hero?" +
                "$IMAGE_URL={$IMAGE_URL}&" +
                "$NAME={$NAME}&" +
                "$DESCRIPTION={$DESCRIPTION}",
            arguments = listOf(
                navArgument(IMAGE_URL) { type = NavType.StringType },
                navArgument(NAME) { type = NavType.StringType },
                navArgument(DESCRIPTION) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            HeroScreen(
                imageUrl = backStackEntry.arguments?.getString(IMAGE_URL)
                    ?: error("No value passed for $IMAGE_URL"),
                name = backStackEntry.arguments?.getString(NAME)
                    ?: error("No value passed for $NAME"),
                description = backStackEntry.arguments?.getString(DESCRIPTION)
                    ?: error("No value passed for $DESCRIPTION"),
            )
        }
    }
}
