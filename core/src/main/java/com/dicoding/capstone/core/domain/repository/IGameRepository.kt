package com.dicoding.capstone.core.domain.repository

import com.dicoding.capstone.core.data.Resource
import com.dicoding.capstone.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getAllGame(): Flow<Resource<List<Game>>>
    fun getFavoriteGame(): Flow<List<Game>>
    fun setFavoriteGame(game: Game, state: Boolean)
}