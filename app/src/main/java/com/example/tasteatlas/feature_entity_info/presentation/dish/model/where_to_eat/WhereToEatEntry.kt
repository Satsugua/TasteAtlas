package com.example.tasteatlas.feature_entity_info.presentation.dish.model.where_to_eat

data class WhereToEatEntry (
    val Address: String,
    val AggregatedCommentCount: Int,
    val AggregatedCriticCommentCount: Int,
    val AggregatedScore: Int,
    val Category: Any?,
    val CountryName: String,
    val CountryUrl: String,
    val Critic: Critic,
    val CriticCount: Int,
    val Critics: List<Critic>,
    val DishRestaurantMultiplier: Int,
    val DishUrl: String,
    val Distance: Any?,
    val Id: Int,
    val Image: String,
    val Name: String,
    val RegionEmblemImage: Any?,
    val RegionId: Int,
    val RegionName: String,
    val RegionUrl: String,
    val UrlLink: String
)