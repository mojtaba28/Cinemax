package com.example.cinemax.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.cinemax.domain.usecase.HomeUseCase
import com.example.cinemax.presentation.state.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
):ViewModel() {
    var state by mutableStateOf<ResponseState>(ResponseState.Idle)
        private set

    private val _postersState = MutableStateFlow<ResponseState>(ResponseState.Idle)
    val postersState = _postersState

    private val _moviesState = MutableStateFlow<ResponseState>(ResponseState.Idle)
    val moviesState = _moviesState

    private val _searchMoviesState = MutableStateFlow<ResponseState>(ResponseState.Idle)
    val searchMoviesState = _searchMoviesState

    private val _categoryState = MutableStateFlow<ResponseState>(ResponseState.Idle)
    val categoryState = _categoryState


    init {
        getMostPopularMovies()
        getPosters()
        getCategory()
    }


    fun getPosters() {

        viewModelScope.launch {
            try {
                state=ResponseState.Loading
                val response = useCase.getPosters()
                state = ResponseState.Success(response)
                _postersState.value = ResponseState.Success(response)
            }catch (e:Exception){
                Log.i("exception",e.message.toString())
                state=ResponseState.Error(e.message.toString())
            }
        }
    }

    fun getMostPopularMovies() {

        viewModelScope.launch {
            try {
                state=ResponseState.Loading
                val response = useCase.getMostPopularMovies()
                state = ResponseState.Success(response)
                _moviesState.value = ResponseState.Success(response)
            }catch (e:Exception){
                Log.i("exception",e.message.toString())
                state=ResponseState.Error(e.message.toString())
            }
        }
    }

    fun getCategory() {
        viewModelScope.launch {
            try {
                state=ResponseState.Loading
                val response = useCase.getCategory()
                state = ResponseState.Success(response)
                _categoryState.value=ResponseState.Success(response)
            }catch (e:Exception){
                Log.i("exception",e.message.toString())
                state=ResponseState.Error(e.message.toString())
            }
        }
    }

    fun getSearchedMovies(name:String?) {
        viewModelScope.launch {
            try {
                state=ResponseState.Loading
                val response = useCase.getSearchedMovies(name?:"")
                state = ResponseState.Success(response)
                _searchMoviesState.value = ResponseState.Success(response)
            }catch (e:Exception){
                Log.i("exception",e.message.toString())
                state=ResponseState.Error(e.message.toString())
            }
        }
    }





}