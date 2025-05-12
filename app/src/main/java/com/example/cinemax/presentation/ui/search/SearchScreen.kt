package com.example.cinemax.presentation.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cinemax.R
import com.example.cinemax.domain.model.Category
import com.example.cinemax.domain.model.Movies
import com.example.cinemax.presentation.components.CinemaxTextField
import com.example.cinemax.presentation.components.SearchMovieItem
import com.example.cinemax.presentation.state.ResponseState
import com.example.cinemax.presentation.ui.theme.CinemaxTheme
import com.example.cinemax.presentation.ui.theme.PrimaryColor
import com.example.cinemax.presentation.ui.theme.TextSecondary
import com.example.cinemax.presentation.viewmodel.HomeViewModel

@Composable
fun SearchScreen() {

    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.state
    val movieState = viewModel.searchMoviesState.collectAsState()
    var search by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {


        //search
        CinemaxTextField(
            value = search,
            onValueChange = {
                search = it
                viewModel.getSearchedMovies(it)
            },
            label = stringResource(R.string.type_name_of_movie),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = TextSecondary
                )
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        when(val result=movieState.value){

            is ResponseState.Loading->{ }

            is ResponseState.Success->{
                val movies=result.data as Movies
                LazyColumn {
                    items(movies) { movie ->
                        SearchMovieItem(movie)
                    }
                }
            }
            is ResponseState.Error->{

            }

            else->{
                if (search.isBlank()) {
                    NoResultsFound()
                }
            }
        }


    }
}

@Composable
fun NoResultsFound() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_result_ic),
            contentDescription = "No Results",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.no_movie_found),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.type_name_of_movie),
            style = MaterialTheme.typography.titleSmall
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF1F1D2B)
@Composable
fun PreviewSearchScreen() {

    CinemaxTheme {
        SearchScreen()
    }
}
