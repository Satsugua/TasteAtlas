package com.example.tasteatlas.feature_favorites.presentation.fav_list

import com.example.tasteatlas.feature_favorites.domain.model.Entity
import com.example.tasteatlas.feature_favorites.domain.util.FavOrder
import com.example.tasteatlas.feature_favorites.domain.util.OrderType

data class FavStates (
    val favs: List<Entity> = emptyList(),
    val favOrder: FavOrder = FavOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)