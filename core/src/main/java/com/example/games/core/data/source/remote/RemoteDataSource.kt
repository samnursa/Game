package com.example.games.core.data.source.remote

import com.example.games.core.data.source.remote.network.ApiResponse
import com.example.games.core.data.source.remote.network.ApiService
import com.example.games.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val gameService: ApiService
    ){

    fun getAllGame(query: String?): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = gameService.getGames(query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    Timber.d("check game : ${response.results}")
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}
