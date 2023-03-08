package com.example.games.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResponse(
    val id: Int,
    val name: String,
    val released: String? = "-",
    val background_image: String? = "",
    val rating: Float,
    val genres: List<Genre>?,
    var updated: String,
    var short_screenshots: List<Screenshots>?,
    var stores: List<Stores>?
): Parcelable {
    @Parcelize
    data class Genre(
        val name: String?
    ): Parcelable

    @Parcelize
    data class Screenshots(
        val image: String?
    ): Parcelable

    @Parcelize
    data class Stores(
        val store: Store?
    ): Parcelable{
        @Parcelize
        data class Store(
            val slug: String?,
            val domain: String?
        ): Parcelable
    }
}