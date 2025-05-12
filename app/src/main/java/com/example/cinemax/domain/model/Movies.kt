package com.example.cinemax.domain.model


import androidx.annotation.Keep

class Movies : ArrayList<Movies.MoviesItem>(){
    @Keep
    data class MoviesItem(
        val category: String?,
        val description: String?,
        val id: Int?,
        val imageUrl: String?,
        val isPopular: Boolean?,
        val name: String?,
        val poster: String?,
        val publishDate: String?,
        val rate: String?,
        val isPremium: Boolean=true,
    )
}