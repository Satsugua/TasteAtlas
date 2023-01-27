package com.example.tasteatlas.feature_favorites.domain.repository

import com.example.tasteatlas.feature_favorites.domain.model.Entity
import kotlinx.coroutines.flow.Flow

interface FavRepository {

    fun getFavs(): Flow<List<Entity>>

    suspend fun insertFav(entity: Entity)

    suspend fun deleteFav(entity: Entity)
}