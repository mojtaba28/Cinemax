package com.example.cinemax.domain.usecase

import com.example.cinemax.domain.model.Category
import com.example.cinemax.domain.model.LoginResponse
import com.example.cinemax.domain.model.Movies
import com.example.cinemax.domain.model.Posters
import com.example.cinemax.domain.model.RegisterResponse
import com.example.cinemax.domain.repository.AuthRepository
import com.example.cinemax.domain.repository.HomeRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val repository: HomeRepository
) {

    suspend  fun getPosters(): Movies {
        return repository.getPosters()
    }

    suspend  fun getMostPopularMovies(): Movies {
        return repository.getMostPopularMovie()
    }

    suspend  fun getCategory(): Category {
        return repository.getCategory()
    }

    suspend  fun getSearchedMovies(name:String): Movies {
        return repository.getSearchedMovies(name)
    }


}