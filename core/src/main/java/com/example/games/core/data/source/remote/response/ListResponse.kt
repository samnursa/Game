package com.example.games.core.data.source.remote.response

data class ListResponse<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)