package com.example.tasteatlas.feature_entity_info.presentation.dish.model.recipe

data class Component(
    val Ingredients: List<Ingredient>,
    val Name: String,
    val Order: Int,
    val RecipeComponentId: Int,
    val TimeFrom: Any,
    val TimeTo: Any
)