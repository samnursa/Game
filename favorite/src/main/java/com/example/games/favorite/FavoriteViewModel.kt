package com.example.games.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.games.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    gamesUseCase: GameUseCase
): ViewModel() {
    val favoriteGame = gamesUseCase.getFavoriteGame().asLiveData()
}