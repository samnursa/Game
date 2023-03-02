package com.example.games.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games_table")
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    var local_id: Int = 0,
    val id: Int,
    val name: String,
    val released: String,
    val background_image: String,
    val rating: Float,
    val genres: List<Genre>,
    var isFavorite: Boolean = false,
    var updated: String,
    var short_screenshots: List<Screenshots>,
    var stores: List<Stores>
){
    data class Genre(
        val name: String
    )

    data class Screenshots(
        val image: String
    )

    data class Stores(
        val store: Store
    ){
        data class Store(
            val slug: String?,
            val domain: String?
        )
    }
}