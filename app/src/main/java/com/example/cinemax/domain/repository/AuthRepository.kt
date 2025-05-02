package com.example.cinemax.domain.repository

import com.example.cinemax.domain.model.LoginResponse
import com.example.cinemax.domain.model.RegisterResponse

interface AuthRepository {
    suspend fun login(email:String,password:String):LoginResponse
    suspend fun register(fullName:String,email:String,password:String):RegisterResponse
}