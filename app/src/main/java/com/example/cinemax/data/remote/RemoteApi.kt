package com.example.cinemax.data.remote

import com.example.cinemax.domain.model.LoginRequest
import com.example.cinemax.domain.model.LoginResponse
import com.example.cinemax.domain.model.RegisterRequest
import com.example.cinemax.domain.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteApi {

    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

}