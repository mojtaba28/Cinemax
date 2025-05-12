package com.example.cinemax.domain.repository

import com.example.cinemax.domain.model.Category
import com.example.cinemax.domain.model.LoginResponse
import com.example.cinemax.domain.model.Movies
import com.example.cinemax.domain.model.Posters
import com.example.cinemax.domain.model.RegisterResponse

interface HomeRepository {
    suspend fun getPosters():Movies
    suspend fun getMostPopularMovie(): Movies
    suspend fun getCategory(): Category
    suspend fun getSearchedMovies(name:String): Movies
}