package com.dicoding.capstone.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.capstone.core.domain.usecase.GameUseCase

class MainViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val game = gameUseCase.getAllGame().asLiveData()
}