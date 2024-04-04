package com.dicoding.capstone.core.domain.usecase

import com.dicoding.capstone.core.data.Resource
import com.dicoding.capstone.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGame(): Flow<Resource<List<Game>>>
    fun getFavoriteGame(): Flow<List<Game>>
    fun setFavoriteGame(game: Game, state: Boolean)
}