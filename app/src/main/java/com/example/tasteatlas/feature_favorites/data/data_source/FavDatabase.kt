package com.example.tasteatlas.feature_favorites.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tasteatlas.feature_favorites.domain.model.Entity


@Database(
    entities = [Entity::class],
    version = 1
)

abstract class FavDatabase: RoomDatabase() {
    abstract val favDao: FavDao

    companion object{
        const val DATABASE_NAME = "fav_db"
    }
}