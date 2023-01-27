package com.example.tasteatlas.feature_entity_info.presentation.dish.model.comments

data class CommentEntry(
    val commentId: Int,
    val commentContent: String,
    val commentCreatedAt: String,
    val commentFirstName: String,
    val commentRating: Int,
    val commentPicture: Any?
)
