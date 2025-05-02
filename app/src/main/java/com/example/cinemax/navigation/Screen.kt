package com.example.cinemax.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Auth : Screen("auth")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Main : Screen("main")
}