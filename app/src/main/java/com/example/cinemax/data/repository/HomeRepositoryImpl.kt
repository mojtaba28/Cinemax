package com.example.cinemax.data.repository

import com.example.cinemax.data.remote.RemoteApi
import com.example.cinemax.domain.model.Category
import com.example.cinemax.domain.model.LoginRequest
import com.example.cinemax.domain.model.LoginResponse
import com.example.cinemax.domain.model.Movies
import com.example.cinemax.domain.model.Posters
import com.example.cinemax.domain.model.RegisterRequest
import com.example.cinemax.domain.model.RegisterResponse
import com.example.cinemax.domain.repository.AuthRepository
import com.example.cinemax.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val api: RemoteApi): HomeRepository {
    override suspend fun getPosters(): Movies {
        return api.getPosters()
    }
    override suspend fun getMostPopularMovie(): Movies {
        return api.getMostPopularMovies()
    }
    override suspend fun getCategory(): Category {
        return api.getCategory()
    }

    override suspend fun getSearchedMovies(name:String): Movies {
        return api.searchMovies(name)
    }


}