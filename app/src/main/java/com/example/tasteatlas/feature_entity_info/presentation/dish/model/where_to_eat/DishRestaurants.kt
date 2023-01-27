package com.example.tasteatlas.feature_entity_info.presentation.dish.model.where_to_eat

data class DishRestaurants(
    val Count: Int,
    val Data: List<WhereToEat>,
    val NumberOfPages: Int,
    val PageIndex: Int,
    val PageSize: Int
)