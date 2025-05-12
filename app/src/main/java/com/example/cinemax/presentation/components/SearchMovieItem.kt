package com.example.cinemax.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cinemax.R
import com.example.cinemax.domain.model.Movies
import com.example.cinemax.presentation.ui.search.SearchScreen
import com.example.cinemax.presentation.ui.theme.CinemaxTheme
import com.example.cinemax.presentation.ui.theme.OrangeColor
import com.example.cinemax.presentation.ui.theme.SecondaryColor
import com.example.cinemax.presentation.ui.theme.SoftPrimaryColor
import com.example.cinemax.presentation.ui.theme.TextSecondary


@Composable
fun SearchMovieItem(movie: Movies.MoviesItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(SoftPrimaryColor, shape = RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        // Movie Poster
        AsyncImage(
            model = movie.imageUrl,
            contentDescription = movie.name,
            modifier = Modifier
                .width(100.dp)
                .height(140.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Right section
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(140.dp)
                .padding(vertical = 4.dp)
        ) {
            // Rating & Premium tag
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .background(OrangeColor, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text("★ ${movie.rate}", style = MaterialTheme.typography.titleSmall)
                }

                Spacer(modifier = Modifier.width(6.dp))

                if (movie.isPremium) {
                    Box(
                        modifier = Modifier
                            .background(OrangeColor, shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(stringResource(R.string.premium), style = MaterialTheme.typography.titleSmall)
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = movie.name?:"",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Info Row 1: Year | Duration | Age limit
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(movie.publishDate?:"", color = TextSecondary)
                Spacer(modifier = Modifier.width(8.dp))
                Text("2h", color = TextSecondary)
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .background(SecondaryColor, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text("+18", color = Color.White, style = MaterialTheme.typography.labelSmall)
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Info Row 2: Genre | Type
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(movie.category?:"", color = TextSecondary)
                Spacer(modifier = Modifier.width(4.dp))
                Text("|", color = TextSecondary)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Movie", color = TextSecondary)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1F1D2B)
@Composable
fun PreviewSearchMovieItem() {
    CinemaxTheme {
        SearchMovieItem(Movies.MoviesItem("","",
            1,"",true,"","","",""))
    }
}
