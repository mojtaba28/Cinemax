package com.example.cinemax.presentation.ui.main
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinemax.presentation.ui.download.DownloadScreen
import com.example.cinemax.presentation.ui.home.HomeScreen
import com.example.cinemax.presentation.ui.navigation.BottomNavItem
import com.example.cinemax.presentation.ui.navigation.BottomNavigationBar
import com.example.cinemax.presentation.ui.profile.ProfileScreen
import com.example.cinemax.presentation.ui.search.SearchScreen
import com.example.cinemax.presentation.ui.theme.CinemaxTheme
import com.example.cinemax.presentation.ui.theme.PrimaryColor


@Composable
fun MainScreen(navController: NavController) {

    val bottomNavController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigationBar(bottomNavController) },
        containerColor = PrimaryColor) {paddingValues ->

        NavHost(
            navController=bottomNavController,
            startDestination =BottomNavItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ){
            composable(BottomNavItem.Home.route) { HomeScreen() }
            composable(BottomNavItem.Search.route) { SearchScreen() }
            composable(BottomNavItem.Download.route) { DownloadScreen() }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
        }

    }

}

@Preview(showBackground = true, backgroundColor = 0xFF1F1D2B)
@Composable
fun PreviewMainScreen() {
    CinemaxTheme {
        MainScreen(navController = rememberNavController())
    }
}