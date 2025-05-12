package com.example.cinemax.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.cinemax.domain.usecase.AuthUseCase
import com.example.cinemax.presentation.state.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCase: AuthUseCase
):ViewModel() {
    var state by mutableStateOf<ResponseState>(ResponseState.Idle)
        private set

    fun login(email:String,password:String) {

        viewModelScope.launch {
            try {
                state=ResponseState.Loading
                val response = useCase.login(email, password)
                state = ResponseState.Success(response)
            }catch (e:Exception){
                Log.i("exception",e.message.toString())
                state=ResponseState.Error(e.message.toString())
            }
        }
    }

    fun register(fullName:String,email:String,password:String) {

        viewModelScope.launch {
            try {
                state=ResponseState.Loading
                val response = useCase.register(fullName,email, password)
                state = ResponseState.Success(response)
            }catch (e:Exception){
                Log.i("exception",e.message.toString())
                state=ResponseState.Error(e.message.toString())
            }
        }
    }


}