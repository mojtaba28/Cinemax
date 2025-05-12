package com.example.cinemax.presentation.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cinemax.R
import com.example.cinemax.presentation.ui.main.MainScreen
import com.example.cinemax.presentation.ui.theme.CinemaxTheme
import com.example.cinemax.presentation.ui.theme.PrimaryColor
import com.example.cinemax.presentation.ui.theme.SecondaryColor
import com.example.cinemax.presentation.ui.theme.TextPrimary

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items= listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Download,
        BottomNavItem.Profile,
    )

    val currentRoute = currentRoute(navController)

    BottomNavigation(
        backgroundColor = PrimaryColor,
        contentColor = Color.Gray
    ) {

        items.forEach { item->

            val selected=currentRoute==item.route
            BottomNavigationItem(

                icon = {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (selected) SecondaryColor.copy(alpha = 0.2f)
                                else Color.Transparent
                            )
                            .padding(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = item.label,
                            tint = if (selected) Color(0xFF00D9FF) else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                label = {
                    Text(text = item.label,
                        color = if (selected) SecondaryColor else TextPrimary, fontSize = 12.sp)
                },
                selected = selected,
                onClick = {
                    if (!selected){
                        navController.navigate(item.route){
                            popUpTo(BottomNavItem.Home.route)
                            launchSingleTop=true
                        }
                    }
                },
                selectedContentColor = SecondaryColor,
                unselectedContentColor = Color.Gray
            )
        }
    }

}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview(showBackground = true, backgroundColor = 0xFF1F1D2B)
@Composable
fun PreviewBottomNavigationBar() {
    CinemaxTheme {
        BottomNavigationBar(navController = rememberNavController())
    }
}

