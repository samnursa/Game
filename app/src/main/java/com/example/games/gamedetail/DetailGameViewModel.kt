package com.example.games.gamedetail

import androidx.lifecycle.ViewModel
import com.example.games.core.domain.model.Game
import com.example.games.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailGameViewModel @Inject constructor(
    private val gamesUseCase: GameUseCase
): ViewModel() {
    fun setFavoriteGame(game: Game, newStatus:Boolean) =
        gamesUseCase.setFavoriteGame(game, newStatus)
}