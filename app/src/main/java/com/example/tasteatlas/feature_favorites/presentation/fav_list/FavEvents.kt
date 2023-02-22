package com.example.tasteatlas.feature_favorites.presentation.fav_list

import com.example.tasteatlas.feature_favorites.domain.util.FavOrder
sealed class FavEvents {
    data class Order(val favOrder: FavOrder): FavEvents()
    object ToggleOrder: FavEvents()
}