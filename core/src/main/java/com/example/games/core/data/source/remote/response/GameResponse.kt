package com.example.games.core.data.source.remote.response

data class GameResponse(
    val id: Int,
    val name: String,
    val released: String? = "-",
    val background_image: String? = "",
    val rating: Float,
    val genres: List<Genre>?,
    var updated: String,
    var short_screenshots: List<Screenshots>,
    var stores: List<Stores>
){
    data class Genre(
        val name: String?
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