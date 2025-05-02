package com.example.cinemax.presentation.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


import com.example.cinemax.presentation.ui.theme.CinemaxTheme
import com.example.cinemax.presentation.ui.theme.PrimaryColor
import com.example.cinemax.presentation.ui.theme.SecondaryColor
import kotlinx.coroutines.launch
import com.google.accompanist.pager.*
@Composable
fun OnboardingScreen(

    navController: NavController
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor)
    ) {
        HorizontalPager(
            count = onboardingPages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPageView(page = onboardingPages[page])
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = SecondaryColor,
                inactiveColor = SecondaryColor.copy(alpha = 0.4f),
                modifier = Modifier.padding(8.dp)
            )

            // 🔷 دکمه سفارشی
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(SecondaryColor)
                    .border(
                        width = 2.dp,
                        color = SecondaryColor,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage == onboardingPages.lastIndex) {

                                navController.navigate("auth") {
                                    popUpTo("onboarding") { inclusive = true } // حذف از بک‌استک
                                }

                            } else {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = PrimaryColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1F1D2B)
@Composable
fun PreviewOnboardingScreen() {
    CinemaxTheme {
        OnboardingScreen(navController = rememberNavController() )
    }
}
