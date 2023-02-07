package com.example.tasteatlas.feature_favorites.domain.use_case

import com.example.tasteatlas.feature_favorites.domain.repository.FavRepository

class CheckIfFav(
    private val repository: FavRepository
)  {
    suspend operator fun invoke(id: Int) : Boolean {
        return repository.checkIfFav(id)
    }
}