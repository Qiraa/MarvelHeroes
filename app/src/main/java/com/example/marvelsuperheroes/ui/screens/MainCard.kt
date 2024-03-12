package com.example.marvelsuperheroes.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun MainCard(modifier: Modifier = Modifier, imageUrl: String,name: String) {
    Card(modifier = modifier) {
       Box(contentAlignment = Alignment.BottomStart, modifier = Modifier.fillMaxSize()){
           Image(
               painter = rememberAsyncImagePainter(imageUrl),
               contentDescription = null,
               modifier = Modifier.matchParentSize(),
               contentScale = ContentScale.Crop,
           )
           Text(
               text = name,
               style = MaterialTheme.typography.headlineLarge,
               modifier = Modifier.padding(start = 16.dp, bottom = 32.dp)
           )
       }
    }

}