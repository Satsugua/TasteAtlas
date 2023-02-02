package com.example.tasteatlas.feature_favorites.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}