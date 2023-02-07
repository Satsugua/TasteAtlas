package com.example.tasteatlas.feature_favorites.data.repository

import com.example.tasteatlas.feature_favorites.data.data_source.FavDao
import com.example.tasteatlas.feature_favorites.domain.model.Entity
import com.example.tasteatlas.feature_favorites.domain.repository.FavRepository
import kotlinx.coroutines.flow.Flow

class FavRepositoryImpl(
    private val dao: FavDao
) : FavRepository {

    override fun getFavs(): Flow<List<Entity>> {
        return dao.getFavs()
    }
    override suspend fun insertFav(entity: Entity) {
        return dao.insertFav(entity)
    }
    override suspend fun deleteFav(entity: Entity) {
        return dao.deleteFav(entity)
    }

    override suspend fun checkIfFav(id: Int): Boolean {
        return dao.checkIfFav(id)
    }
}