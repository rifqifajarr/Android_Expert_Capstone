package com.dicoding.capstone.core.utils

import com.dicoding.capstone.core.data.source.local.entity.GameEntity
import com.dicoding.capstone.core.data.source.remote.response.GameResponse
import com.dicoding.capstone.core.domain.model.Game

object DataMapper {

    fun mapResponseToEntities(input: GameResponse): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.results?.map {
            val game = it?.let { it1 ->
                GameEntity(
                    gameId = it.id.toString(),
                    name = it1.name,
                    rating = it.rating.toString(),
                    metacritic = it.metacritic.toString(),
                    released = it.released,
                    playTime = it.playtime.toString(),
                    imageUrl = it.backgroundImage,
                )
            }
            if (game != null) {
                gameList.add(game)
            }
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                gameId = it.gameId,
                name = it.name,
                rating = it.rating,
                metaCritic = it.metacritic,
                released = it.released,
                playTime = it.playTime,
                imageUrl = it.imageUrl,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        gameId = input.gameId,
        name = input.name,
        rating = input.rating,
        metacritic = input.metaCritic,
        released = input.released,
        playTime = input.playTime,
        imageUrl = input.imageUrl
    )
}