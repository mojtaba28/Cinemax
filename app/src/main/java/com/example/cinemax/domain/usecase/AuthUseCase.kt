package com.example.cinemax.domain.usecase

import com.example.cinemax.domain.model.LoginResponse
import com.example.cinemax.domain.model.RegisterResponse
import com.example.cinemax.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend  fun login(email: String, password: String): LoginResponse {
        return repository.login(email,password)
    }
    suspend  fun register(fullName:String,email: String, password: String): RegisterResponse {
        return repository.register(fullName,email,password)
    }
}