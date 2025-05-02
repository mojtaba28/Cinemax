package com.example.cinemax.domain.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val fullName: String,
    val email: String,
    val password: String
)

data class LoginResponse(
    val id: Int,
    val fullName: String,
    val email: String,
)

data class RegisterResponse(
    val id: Int,
    val fullName: String,
    val email: String,
)
