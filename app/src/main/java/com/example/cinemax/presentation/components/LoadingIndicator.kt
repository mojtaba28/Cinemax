package com.example.cinemax.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cinemax.presentation.ui.theme.PrimaryColor

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    size: Int = 20,
    strokeWidth: Int = 2
) {
    CircularProgressIndicator(
        color = Color.White,
        modifier = modifier
            .size(size.dp)
            .padding(start = 8.dp),
        strokeWidth = strokeWidth.dp
    )
}