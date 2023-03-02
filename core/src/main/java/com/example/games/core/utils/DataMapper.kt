package com.example.games.core.utils

import com.example.games.core.data.source.local.entity.GameEntity
import com.example.games.core.data.source.remote.response.GameResponse
import com.example.games.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val genreList = ArrayList<GameEntity.Genre>()
            it.genres?.map{ genre ->
                genreList.add(
                    GameEntity.Genre(
                    name = genre.name ?: ""
                ))
            }

            val screenshots = ArrayList<GameEntity.Screenshots>()
            it.short_screenshots.map{ screenShot ->
                screenshots.add(
                    GameEntity.Screenshots(
                        image = screenShot.image
                    ))
            }

            val stores = ArrayList<GameEntity.Stores>()
            it.stores.map{ store ->
                stores.add(
                    GameEntity.Stores(
                        GameEntity.Stores.Store(
                            slug = store.store.slug ?: "",
                            domain = store.store.domain ?: ""
                        )
                    )
                )
            }

            val game = GameEntity(
                id = it.id,
                name = it.name,
                released = it.released ?: "",
                background_image = it.background_image ?: "",
                rating = it.rating,
                genres = genreList,
                updated = it.updated,
                short_screenshots = screenshots,
                stores = stores
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> {
        val gameList = ArrayList<Game>()
        input.map {
            val genreList = ArrayList<Game.Genre>()
            it.genres.map{ genre ->
                genreList.add(Game.Genre(
                    name = genre.name
                ))
            }

            val screenshots = ArrayList<Game.Screenshots>()
            it.short_screenshots.map{ screenShot ->
                screenshots.add(
                    Game.Screenshots(
                        image = screenShot.image
                    ))
            }

            val stores = ArrayList<Game.Stores>()
            it.stores.map{ store ->
                stores.add(
                    Game.Stores(
                        Game.Stores.Store(
                            slug = store.store.slug ?: "",
                            domain = store.store.domain ?: ""
                        )
                    )
                )
            }

            val game = Game(
                local_id  = it.local_id,
                id = it.id,
                name = it.name,
                released = it.released,
                background_image = it.background_image,
                rating = it.rating,
                genres = genreList,
                isFavorite = it.isFavorite,
                updated = it.updated,
                short_screenshots = screenshots,
                stores = stores
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntityToDomain(input: GameEntity) : Game {
        val genreList = ArrayList<Game.Genre>()
        input.genres.map{
            val genre = Game.Genre(
                name = it.name
            )
            genreList.add(genre)
        }

        val screenshots = ArrayList<Game.Screenshots>()
        input.short_screenshots.map{ screenShot ->
            screenshots.add(
                Game.Screenshots(
                    image = screenShot.image
                ))
        }

        val stores = ArrayList<Game.Stores>()
        input.stores.map{ store ->
            stores.add(
                Game.Stores(
                    Game.Stores.Store(
                        slug = store.store.slug ?: "",
                        domain = store.store.domain ?: ""
                    )
                )
            )
        }

        return Game(
            local_id = input.local_id,
            id = input.id,
            name = input.name,
            released = input.released,
            background_image = input.background_image,
            rating = input.rating,
            genres = genreList,
            isFavorite = input.isFavorite,
            updated = input.updated,
            short_screenshots = screenshots,
            stores = stores
        )
    }

    fun mapDomainToEntity(input: Game) : GameEntity {
        val genreList = ArrayList<GameEntity.Genre>()
        input.genres.map{
            val genre = GameEntity.Genre(
                name = it.name
            )
            genreList.add(genre)
        }

        val screenshots = ArrayList<GameEntity.Screenshots>()
        input.short_screenshots.map{ screenShot ->
            screenshots.add(
                GameEntity.Screenshots(
                    image = screenShot.image
                ))
        }

        val stores = ArrayList<GameEntity.Stores>()
        input.stores.map{ store ->
            stores.add(
                GameEntity.Stores(
                    GameEntity.Stores.Store(
                        slug = store.store.slug ?: "",
                        domain = store.store.domain ?: ""
                    )
                )
            )
        }

        return GameEntity(
            local_id = input.local_id,
            id = input.id,
            name = input.name,
            released = input.released,
            background_image = input.background_image,
            rating = input.rating,
            genres = genreList,
            isFavorite = input.isFavorite,
            updated = input.updated,
            short_screenshots = screenshots,
            stores = stores
        )
    }
}