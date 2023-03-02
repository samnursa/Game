package com.example.games.core.data.source.remote.network

import com.example.games.core.data.source.remote.response.GameResponse
import com.example.games.core.data.source.remote.response.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGames(
        @Query("search") search: String? = "",
        @Query("page") page: String? = "1",
        @Query("page_size") page_size: Int? = 20,
        @Query("key") api_key: String? = "56d8c68cc06143cc90cd78d84f91a891"
    ): ListResponse<GameResponse>
}