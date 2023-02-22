package com.example.tasteatlas.feature_favorites.domain.util

sealed class FavOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): FavOrder(orderType)
    class Date(orderType: OrderType): FavOrder(orderType)
    fun copy(orderType: OrderType): FavOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
        }
    }
}