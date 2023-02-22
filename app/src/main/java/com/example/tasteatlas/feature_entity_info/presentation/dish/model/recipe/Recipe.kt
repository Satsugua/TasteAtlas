package com.example.tasteatlas.feature_entity_info.presentation.dish.model.recipe

data class Recipe(
    val Components: List<Component>,
    val Description: String,
    val EntityUrlLink: String,
    val Image: Image,
    val Name: String,
    val Time: Time,
    val UrlLink: String
)