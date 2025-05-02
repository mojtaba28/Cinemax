package com.example.cinemax.di

import com.example.cinemax.data.remote.RemoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAuthApi(): RemoteApi {
        return Retrofit.Builder()
            .baseUrl("http://192.168.179.252:8080/") // اگر با localhost کار می‌کنی در emulator
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteApi::class.java)
    }

}