package com.example.cinemax.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cinemax.presentation.ui.Auth.AuthScreen
import com.example.cinemax.presentation.ui.Auth.LoginScreen
import com.example.cinemax.presentation.ui.Auth.SignUpScreen
import com.example.cinemax.presentation.ui.main.MainScreen
import com.example.cinemax.presentation.ui.onboarding.OnboardingScreen
import com.example.cinemax.presentation.ui.splash.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding.route
    ) {
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Onboarding.route) { OnboardingScreen(navController) }
        composable(Screen.Auth.route) { AuthScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Main.route) { MainScreen(navController) }
        composable(Screen.SignUp.route) { SignUpScreen(navController) }
    }
}