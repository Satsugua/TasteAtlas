package com.example.tasteatlas.feature_favorites.domain.use_case

import com.example.tasteatlas.feature_favorites.domain.model.Entity
import com.example.tasteatlas.feature_favorites.domain.model.InvalidFavException
import com.example.tasteatlas.feature_favorites.domain.repository.FavRepository

class AddFav(
    private val repository: FavRepository
) {
    @Throws(InvalidFavException::class)
    suspend operator fun invoke(fav: Entity) {
        repository.insertFav(fav)
    }
}