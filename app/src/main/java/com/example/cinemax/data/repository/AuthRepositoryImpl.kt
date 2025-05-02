package com.example.cinemax.data.repository

import com.example.cinemax.data.remote.RemoteApi
import com.example.cinemax.domain.model.LoginRequest
import com.example.cinemax.domain.model.LoginResponse
import com.example.cinemax.domain.model.RegisterRequest
import com.example.cinemax.domain.model.RegisterResponse
import com.example.cinemax.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: RemoteApi): AuthRepository {
    override suspend fun login(email: String, password: String): LoginResponse {

        return api.login(LoginRequest(email,password))
    }

    override suspend fun register(
        fullName: String,
        email: String,
        password: String
    ): RegisterResponse {
        return api.register(RegisterRequest(fullName,email,password))
    }
}