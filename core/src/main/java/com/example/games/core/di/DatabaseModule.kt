package com.example.games.core.di

import android.content.Context
import androidx.room.Room
import com.example.games.core.data.source.local.room.GamesDao
import com.example.games.core.data.source.local.room.GamesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GamesDatabase = Room.databaseBuilder(
        context,
        GamesDatabase::class.java, "Games.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideGameDao(database: GamesDatabase): GamesDao = database.gamesDao()
}