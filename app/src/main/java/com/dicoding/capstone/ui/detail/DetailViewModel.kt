package com.dicoding.capstone.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.capstone.core.domain.model.Game
import com.dicoding.capstone.core.domain.usecase.GameUseCase

class DetailViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun setFavoriteGame(game: Game, newStatus: Boolean) =
        gameUseCase.setFavoriteGame(game, newStatus)
}