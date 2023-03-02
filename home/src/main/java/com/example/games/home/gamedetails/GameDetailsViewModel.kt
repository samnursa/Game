package com.example.games.home.gamedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.games.core.domain.model.Game
import com.example.games.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val gamesUseCase: GameUseCase
): ViewModel() {
    fun detailsGame(id: String) = gamesUseCase.getDetailsGame(id).asLiveData()
    fun setFavoriteGame(game: Game, newStatus:Boolean) =
        gamesUseCase.setFavoriteGame(game, newStatus)
}