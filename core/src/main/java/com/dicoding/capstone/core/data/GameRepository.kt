package com.dicoding.capstone.core.data

import android.util.Log
import com.dicoding.capstone.core.data.source.local.LocalDataSource
import com.dicoding.capstone.core.data.source.remote.RemoteDataSource
import com.dicoding.capstone.core.data.source.remote.network.ApiResponse
import com.dicoding.capstone.core.data.source.remote.response.GameResponse
import com.dicoding.capstone.core.domain.model.Game
import com.dicoding.capstone.core.domain.repository.IGameRepository
import com.dicoding.capstone.core.utils.AppExecutors
import com.dicoding.capstone.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {
    override fun getAllGame(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, GameResponse>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> =
                remoteDataSource.getAllGame()

            override suspend fun saveCallResult(data: GameResponse) {
                val gameList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGame(gameList)
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data.isNullOrEmpty()

        }.asFlow()

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
        Log.i("GameRepository", game.name + " added to favorite")
    }
}