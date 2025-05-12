package com.example.cinemax.presentation.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.cinemax.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.cinemax.domain.model.Category
import com.example.cinemax.domain.model.Movies
import com.example.cinemax.presentation.state.ResponseState
import com.example.cinemax.presentation.ui.slider.PosterSlider
import com.example.cinemax.presentation.ui.theme.PrimaryColor
import com.example.cinemax.presentation.ui.theme.SecondaryColor
import com.example.cinemax.presentation.ui.theme.TextPrimary
import com.example.cinemax.presentation.ui.theme.TextSecondary
import com.example.cinemax.presentation.viewmodel.HomeViewModel
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.state
    val posterState = viewModel.postersState.collectAsState()
    val mostPopularMovieState = viewModel.moviesState.collectAsState()
    val categoryState=viewModel.categoryState.collectAsState()

    var search by remember { mutableStateOf("") }
    val categories = listOf("All", "Comedy", "Animation", "Dokumenter")
    val selectedCategory = remember { mutableStateOf("All") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(PrimaryColor)
            .padding(16.dp)
    ) {
        // Top bar with user info and favorite icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id =R.drawable.profile_ic),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Hello, Mojtaba", style = MaterialTheme.typography.titleMedium)
                    Text(stringResource(R.string.let_s_stream_your_favorite_movie), style = MaterialTheme.typography.bodySmall)
                }
            }
            IconButton(onClick = { /* TODO */ }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorites",
                    tint = Color.Red,
                    modifier = Modifier
                        .background(Color(0xFF23234D), shape = CircleShape)
                        .padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Featured carousel (simplified)
        Text(stringResource(R.string.featured), style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(16.dp))

        when (val result=posterState.value) {
            is ResponseState.Loading -> {

            }
            is ResponseState.Success -> {
                val posters = result.data as Movies
                PosterSlider(movies = posters)
            }
            is ResponseState.Error -> {

            }
            else -> {

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Categories
        Text(stringResource(R.string.categories), style = MaterialTheme.typography.titleMedium)
        when(val result=categoryState.value){

            is ResponseState.Loading-> {
                LazyRow {
                    items(7) { // فرضاً ۴ اسکلتون
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp, top = 8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .placeholder(
                                    visible = true,
                                    highlight = PlaceholderHighlight.shimmer(),
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .size(width = 50.dp, height = 30.dp)
                        )
                    }
                }
            }

                is ResponseState.Success->{
                    val categories=result.data as Category
                    LazyRow {
                        items(categories){ category->
                            val isSelected=selectedCategory.value==category.name
                            Box (modifier = Modifier
                                .padding(end = 8.dp, top = 8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) SecondaryColor else Color(0xFF23234D))
                                .clickable { selectedCategory.value = category.name ?: "" }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                            ){

                                Text(text = category.name?:"", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }


            else->{}
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Most popular
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.most_popular),style = MaterialTheme.typography.titleMedium)
            Text(stringResource(R.string.see_all), style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(8.dp))

        when (val result=mostPopularMovieState.value) {

            is ResponseState.Loading-> {
                LazyRow {
                    items(4) {
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp, top = 8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .placeholder(
                                    visible = true,
                                    highlight = PlaceholderHighlight.shimmer(),
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .size(width = 140.dp, height = 220.dp)
                        )
                    }
                }
            }

            is ResponseState.Success -> {
                val movies = result.data as Movies
                LazyRow {
                    items(movies) { movie ->
                        Column(
                            modifier = Modifier
                                .width(140.dp)
                                .height(220.dp)
                                .padding(end = 12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(10.dp))
                            ) {
                                AsyncImage(
                                    model = movie.imageUrl,
                                    contentDescription = movie.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .background(
                                            Color(0xCC000000),
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text("⭐ ${movie.rate}", color = Color.White, fontSize = 12.sp)
                                }
                            }
                            Text(movie.name?:"", color = Color.White, fontWeight = FontWeight.SemiBold)
                            Text(movie.category?:"", color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                }
            }

            else -> {}
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}