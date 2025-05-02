package com.example.cinemax.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinemax.presentation.ui.theme.CinemaxTheme
import com.example.cinemax.presentation.ui.theme.TextPrimary
import com.example.cinemax.presentation.ui.theme.TextSecondary

import com.example.cinemax.R

@Composable
fun OnboardingPageView(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = Modifier
                .height(280.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = page.title,
            style = MaterialTheme.typography.titleLarge.copy(color = TextPrimary)
            , textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1F1D2B)
@Composable
fun PreviewOnboardingPageView() {
    CinemaxTheme {
        OnboardingPageView(
            page = OnboardingPage(
                imageRes = R.drawable.onboarding_img1,
                title = "The biggest international and local film streaming",
                description = "Semper in cursus magna et eu varius nunc adipiscing. Elementum justo, laoreet id sem semper parturient"
            )
        )
    }
}

