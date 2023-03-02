package com.example.games.core.data

import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.games.core.data.source.local.LocalDataSource
import com.example.games.core.data.source.remote.RemoteDataSource
import com.example.games.core.data.source.remote.network.ApiResponse
import com.example.games.core.data.source.remote.response.GameResponse
import com.example.games.core.domain.model.Game
import com.example.games.core.domain.repository.IGameRepository
import com.example.games.core.utils.AppExecutors
import com.example.games.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {
    override fun getAllGames(query: String?): Flow<Resource<List<Game>>> =
        object : com.example.games.core.data.NetworkBoundResource<List<Game>, List<GameResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Game>> {
                var queryBuilder = "SELECT * FROM games_table"
                if(query != ""){
                    queryBuilder += " WHERE name LIKE '%$query%'"
                }
                queryBuilder += " LIMIT 20"
                return localDataSource.getAllGame(SimpleSQLiteQuery(queryBuilder)).map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Game>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getAllGame(query)

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asFlow()



    override fun getFavoriteGames(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun getDetailsGame(id: String): Flow<Game> {
        return localDataSource.getDetailGame(id).map { DataMapper.mapEntityToDomain(it) }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }
}