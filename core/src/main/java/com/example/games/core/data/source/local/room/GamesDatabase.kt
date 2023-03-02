package com.example.games.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.games.core.data.source.local.converter.Converters
import com.example.games.core.data.source.local.entity.GameEntity

@Database(entities = [
    GameEntity::class
], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GamesDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}