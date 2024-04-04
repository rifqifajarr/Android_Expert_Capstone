package com.dicoding.capstone.core.domain.usecase

import com.dicoding.capstone.core.data.Resource
import com.dicoding.capstone.core.domain.model.Game
import com.dicoding.capstone.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getAllGame(): Flow<Resource<List<Game>>> =
        gameRepository.getAllGame()

    override fun getFavoriteGame(): Flow<List<Game>> =
        gameRepository.getFavoriteGame()

    override fun setFavoriteGame(game: Game, state: Boolean) =
        gameRepository.setFavoriteGame(game, state)
}