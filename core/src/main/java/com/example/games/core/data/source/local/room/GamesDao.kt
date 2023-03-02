package com.example.games.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.games.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {
//    @Query("SELECT * FROM games_table")
    @RawQuery(observedEntities = [GameEntity::class])
    fun getAllGames(query: SupportSQLiteQuery): Flow<List<GameEntity>>

    @Query("SELECT * FROM games_table where isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games_table where id = :id")
    fun getDetailGames(id: String): Flow<GameEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(game: List<GameEntity>)

    @Update
    fun updateFavoriteGame(game: GameEntity)
}
