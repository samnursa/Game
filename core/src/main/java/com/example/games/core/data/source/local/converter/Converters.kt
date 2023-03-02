package com.example.games.core.data.source.local.converter

import androidx.room.TypeConverter
import com.example.games.core.data.source.local.entity.GameEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun gameListToJson(list: List<GameEntity.Genre>?): String? {
        return if(list == null) null else Gson().toJson(list)
    }

    @TypeConverter
    fun jsonToGameList(jsonData: String?): List<GameEntity.Genre>? {
        return if (jsonData == null) null else Gson().fromJson(jsonData, object : TypeToken<List<GameEntity.Genre>?>() {}.type)
    }

    @TypeConverter
    fun screenshotsToJson(list: List<GameEntity.Screenshots>?): String? {
        return if(list == null) null else Gson().toJson(list)
    }

    @TypeConverter
    fun jsonToScreenshots(jsonData: String?): List<GameEntity.Screenshots>? {
        return if (jsonData == null) null else Gson().fromJson(jsonData, object : TypeToken<List<GameEntity.Screenshots>?>() {}.type)
    }

    @TypeConverter
    fun storeshotsToJson(list: List<GameEntity.Stores>?): String? {
        return if(list == null) null else Gson().toJson(list)
    }

    @TypeConverter
    fun jsonToStores(jsonData: String?): List<GameEntity.Stores>? {
        return if (jsonData == null) null else Gson().fromJson(jsonData, object : TypeToken<List<GameEntity.Stores>?>() {}.type)
    }

    @TypeConverter
    fun storehotsToJson(list: List<GameEntity.Stores.Store>?): String? {
        return if(list == null) null else Gson().toJson(list)
    }

    @TypeConverter
    fun jsonToStore(jsonData: String?): List<GameEntity.Stores.Store>? {
        return if (jsonData == null) null else Gson().fromJson(jsonData, object : TypeToken<List<GameEntity.Stores.Store>?>() {}.type)
    }
}