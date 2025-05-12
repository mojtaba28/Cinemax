package com.example.cinemax.presentation.state

sealed class ResponseState {
    object Idle : ResponseState()
    object Loading : ResponseState()
    data class Success(val data: Any) : ResponseState()
    data class Error(val message: String) : ResponseState()
}