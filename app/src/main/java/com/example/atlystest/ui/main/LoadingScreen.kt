package com.example.atlystest.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
    ) {
        Log.d("Suraj", "LoadingScreen: ")
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Black
        )
    }
}