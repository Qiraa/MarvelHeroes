package com.example.marvelsuperheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.marvelsuperheroes.ui.screens.MainScreen
import com.example.marvelsuperheroes.ui.theme.MarvelSuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelSuperheroesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}