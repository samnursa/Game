package com.example.games.home.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.games.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gamesUseCase: GameUseCase
): ViewModel() {

    val query = MutableLiveData("")
    fun game() = Transformations.switchMap(query) {
        gamesUseCase.getAllGames(it).asLiveData()
    }
}