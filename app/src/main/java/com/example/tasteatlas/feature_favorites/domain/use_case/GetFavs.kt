package com.example.tasteatlas.feature_favorites.domain.use_case

import com.example.tasteatlas.feature_favorites.domain.model.Entity
import com.example.tasteatlas.feature_favorites.domain.repository.FavRepository
import com.example.tasteatlas.feature_favorites.domain.util.FavOrder
import com.example.tasteatlas.feature_favorites.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavs(
    private val repository: FavRepository
) {
    operator fun invoke(
       favOrder: FavOrder = FavOrder.Date(OrderType.Descending)
    ): Flow<List<Entity>> {
        return repository.getFavs().map { favs ->
            when(favOrder.orderType) {

                is OrderType.Ascending -> {
                    when(favOrder) {
                        is FavOrder.Title -> favs.sortedBy { it.Name.lowercase() }
                        is FavOrder.Date -> favs.sortedBy { it.timestamp }
                    }
                }
                is OrderType.Descending -> {
                    when(favOrder) {
                        is FavOrder.Title -> favs.sortedByDescending { it.Name.lowercase() }
                        is FavOrder.Date -> favs.sortedByDescending { it.timestamp }
                    }
                }
            }
        }
    }
}