package com.example.cinemax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinemax.navigation.AppNavGraph
import com.example.cinemax.presentation.ui.Auth.AuthScreen
import com.example.cinemax.presentation.ui.Auth.LoginScreen
import com.example.cinemax.presentation.ui.main.MainScreen
import com.example.cinemax.presentation.ui.onboarding.OnboardingScreen
import com.example.cinemax.presentation.ui.theme.CinemaxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaxTheme  {
                val navController = rememberNavController()

                AppNavGraph(navController = navController)
            }
        }
    }
}
