package com.example.cinemax.di

import com.example.cinemax.data.remote.RemoteApi
import com.example.cinemax.domain.repository.AuthRepository
import com.example.cinemax.data.repository.AuthRepositoryImpl
import com.example.cinemax.data.repository.HomeRepositoryImpl
import com.example.cinemax.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(api: RemoteApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    @Provides
    fun provideHomeRepository(api: RemoteApi): HomeRepository {
        return HomeRepositoryImpl(api)
    }
}