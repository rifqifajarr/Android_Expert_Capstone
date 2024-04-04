package com.dicoding.capstone.core.data.source.remote.network

import com.dicoding.capstone.core.data.source.remote.response.GameResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getList(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 100
    ): GameResponse
}