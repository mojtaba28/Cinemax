package com.example.cinemax.presentation.ui.slider

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cinemax.domain.model.Movies
import com.example.cinemax.domain.model.Posters
import com.example.cinemax.presentation.ui.home.HomeScreen
import com.example.cinemax.presentation.ui.theme.SecondaryColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PosterSlider(movies: Movies) {
    val pagerState = rememberPagerState()

    Column {
        HorizontalPager(
            count = movies.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) { page ->
            val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffset
            val scale = 0.85f + (1 - pageOffset.absoluteValue) * 0.15f
            val movie = movies[page]
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .padding(horizontal = 4.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        alpha = 0.5f + (1 - pageOffset.absoluteValue) * 0.5f
                    }
                    .background(Color.DarkGray)

            ) {
                AsyncImage(
                    model = movie.poster,
                    contentDescription = movie.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(Color(0xAA000000))
                        .padding(8.dp)
                ) {
                    Column {
                        Text(movie.name?:"", color = Color.White, fontWeight = FontWeight.Bold)

                    }
                }
            }
        }

        AnimatedPagerIndicator(
            pagerState = pagerState,
            indicatorCount = movies.size,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 12.dp),
            activeColor = SecondaryColor,
            inactiveColor = Color.Gray
        )
    }
}

@Composable
fun AnimatedPagerIndicator(
    pagerState: com.google.accompanist.pager.PagerState,
    modifier: Modifier = Modifier,
    indicatorCount: Int,
    activeColor: Color = Color.Cyan,
    inactiveColor: Color = Color.Gray
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(indicatorCount) { index ->
            val isSelected = pagerState.currentPage == index
            val animatedSize by animateFloatAsState(
                targetValue = if (isSelected) 10f else 6f,
                label = "indicator-size"
            )
            val animatedAlpha by animateFloatAsState(
                targetValue = if (isSelected) 1f else 0.5f,
                label = "indicator-alpha"
            )

            Box(
                modifier = Modifier
                    .size(animatedSize.dp)
                    .graphicsLayer {
                        alpha = animatedAlpha
                    }
                    .background(
                        color = if (isSelected) activeColor else inactiveColor,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPosterSlider() {
    PosterSlider(Movies())
}
