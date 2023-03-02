package com.example.games.core.domain.usecase

import com.example.games.core.domain.model.Game
import com.example.games.core.domain.repository.IGameRepository
import javax.inject.Inject

class GameInteractor @Inject constructor(private val gamesRepository: IGameRepository) : GameUseCase {
    override fun getAllGames(query: String?) = gamesRepository.getAllGames(query)
    override fun getFavoriteGame() = gamesRepository.getFavoriteGames()
    override fun getDetailsGame(id: String) = gamesRepository.getDetailsGame(id)
    override fun setFavoriteGame(game: Game, state: Boolean) = gamesRepository.setFavoriteGame(game, state)
}