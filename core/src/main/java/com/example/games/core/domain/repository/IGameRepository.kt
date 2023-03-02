package com.example.games.core.domain.repository

import com.example.games.core.data.Resource
import com.example.games.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getAllGames(query: String?): Flow<Resource<List<Game>>>
    fun getFavoriteGames(): Flow<List<Game>>
    fun getDetailsGame(id: String): Flow<Game>
    fun setFavoriteGame(game: Game, state: Boolean)
}