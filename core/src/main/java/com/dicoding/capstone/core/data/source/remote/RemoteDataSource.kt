package com.dicoding.capstone.core.data.source.remote

import android.util.Log
import com.dicoding.capstone.core.data.source.remote.network.ApiResponse
import com.dicoding.capstone.core.data.source.remote.network.ApiService
import com.dicoding.capstone.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllGame(): Flow<ApiResponse<GameResponse>> {
        return flow {
            try {
                val response = apiService.getList()

                if (response != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}