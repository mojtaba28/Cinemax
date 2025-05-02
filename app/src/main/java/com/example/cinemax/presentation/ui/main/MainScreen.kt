package com.example.cinemax.presentation.ui.main
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cinemax.presentation.ui.theme.CinemaxTheme
import com.example.cinemax.presentation.ui.theme.PrimaryColor


@Composable
fun MainScreen(navController: NavController) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor)
    )

}

@Preview(showBackground = true, backgroundColor = 0xFF1F1D2B)
@Composable
fun PreviewSplashScreen() {
    CinemaxTheme {
        MainScreen(navController = rememberNavController())
    }
}