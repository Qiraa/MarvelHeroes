package com.example.marvelsuperheroes.ui

import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelsuperheroes.ui.HeroScreen.HERO_ID
import com.example.marvelsuperheroes.ui.screens.HeroScreen
import com.example.marvelsuperheroes.ui.screens.MainScreen

object MainScreen {

    fun route(): String {
        return "main"
    }
}

object HeroScreen {

    const val HERO_ID = "id"

    fun route(): String {
        return "hero?$HERO_ID={$HERO_ID}"
    }

    fun withHeroId(id: String): String {
        return "hero?$HERO_ID=$id"
    }
}

@Composable
fun SetupNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreen.route(),
        enterTransition = { scaleIn() },
    ) {
        composable(MainScreen.route()) {
            MainScreen(navController = navController)
        }
        composable(
            route = HeroScreen.route(),
            arguments = listOf(
                navArgument(HERO_ID) { type = NavType.StringType },
            )
        ) { backStackEntry ->
            HeroScreen(
                heroId = backStackEntry.arguments?.getString(HERO_ID)
                    ?: error("No value passed for $HERO_ID"),
            )
        }
    }
}
