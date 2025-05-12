package com.example.cinemax.presentation.ui.navigation

import androidx.annotation.DrawableRes
import com.example.cinemax.R
import com.example.cinemax.navigation.Screen

sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int, val label: String) {
    object Home : BottomNavItem(Screen.Home.route, R.drawable.home_ic, "Home")
    object Search : BottomNavItem(Screen.Search.route, R.drawable.search_ic, "Search")
    object Download : BottomNavItem(Screen.Download.route, R.drawable.download_ic, "Download")
    object Profile : BottomNavItem(Screen.Profile.route, R.drawable.profile_ic, "Profile")
}
