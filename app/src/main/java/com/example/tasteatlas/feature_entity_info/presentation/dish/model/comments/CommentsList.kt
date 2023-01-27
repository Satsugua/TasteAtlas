package com.example.tasteatlas.feature_entity_info.presentation.dish.model.comments

data class CommentsList(
    val Count: Int,
    val Data: List<Data>,
    val NumberOfPages: Int,
    val PageIndex: Int,
    val PageSize: Int
)