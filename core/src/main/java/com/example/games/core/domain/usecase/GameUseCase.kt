package com.example.games.core.domain.usecase

import com.example.games.core.data.Resource
import com.example.games.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGames(query: String? = ""): Flow<Resource<List<Game>>>
    fun getDetailsGame(id: String): Flow<Game>
    fun getFavoriteGame(): Flow<List<Game>>
    fun setFavoriteGame(game: Game, state: Boolean)
}