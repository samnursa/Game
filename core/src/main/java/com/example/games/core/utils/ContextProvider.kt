package com.example.games.core.utils

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class ContextProviders {
    val main: CoroutineContext = Dispatchers.Main
    val io: CoroutineContext = Dispatchers.IO

    @Singleton
    @Provides
    fun provideContextProvider(): ContextProviders = ContextProviders()
}