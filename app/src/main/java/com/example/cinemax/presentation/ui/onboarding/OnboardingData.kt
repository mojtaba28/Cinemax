package com.example.cinemax.presentation.ui.onboarding

import com.example.cinemax.R

data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val description: String
)

val onboardingPages = listOf(
    OnboardingPage(
        imageRes = R.drawable.onboarding_img1,
        title = "The biggest international and local film streaming",
        description = "Semper in cursus magna et eu varius nunc adipiscing. Elementum justo, laoreet id sem semper parturient."
    ),
    OnboardingPage(
        imageRes = R.drawable.onboarding_img2,
        title = "Offers ad-free viewing of high quality",
        description = "Semper in cursus magna et eu varius nunc adipiscing. Elementum justo, laoreet id sem semper parturient. "
    ),
    OnboardingPage(
        imageRes = R.drawable.onboarding_img3,
        title = "Our service brings together your favorite series",
        description = "Semper in cursus magna et eu varius nunc adipiscing. Elementum justo, laoreet id sem semper parturient. "
    )
)
