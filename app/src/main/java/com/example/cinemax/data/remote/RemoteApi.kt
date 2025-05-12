package com.example.cinemax.data.remote

import com.example.cinemax.domain.model.Category
import com.example.cinemax.domain.model.LoginRequest
import com.example.cinemax.domain.model.LoginResponse
import com.example.cinemax.domain.model.Movies
import com.example.cinemax.domain.model.Posters
import com.example.cinemax.domain.model.RegisterRequest
import com.example.cinemax.domain.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteApi {

    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @GET("posters")
    suspend fun getPosters(): Movies

    @GET("movies")
    suspend fun getMostPopularMovies(): Movies

    @GET("category")
    suspend fun getCategory(): Category

    @GET("movies/search")
    suspend fun searchMovies(@Query("name") name: String): Movies

}