package com.example.games.core.data.source.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.example.games.core.data.source.local.entity.GameEntity
import com.example.games.core.data.source.local.room.GamesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val gameDao: GamesDao) {
    fun getAllGame(query: SupportSQLiteQuery): Flow<List<GameEntity>> = gameDao.getAllGames(query)

    fun getFavoriteGame(): Flow<List<GameEntity>> = gameDao.getFavoriteGames()

    fun getDetailGame(id: String): Flow<GameEntity> = gameDao.getDetailGames(id)

    suspend fun insertGame(games: List<GameEntity>) = gameDao.insertAll(games)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }
}