package com.example.tasteatlas.feature_favorites.domain.use_case

import com.example.tasteatlas.feature_favorites.domain.model.Entity
import com.example.tasteatlas.feature_favorites.domain.repository.FavRepository

class DeleteFav(
    private val repository: FavRepository
) {
    suspend operator fun invoke(fav: Entity) {
        repository.deleteFav(fav)
    }
}