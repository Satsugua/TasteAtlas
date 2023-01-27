package com.example.tasteatlas.feature_entity_info.presentation.dish.model.comments

data class Data(
    val CommentId: Int,
    val Content: String,
    val CountHelpful: Int,
    val CountNotHelpful: Int,
    val CreatedAt: String,
    val Dislikes: List<Any>,
    val FirstName: String,
    val IsCritic: Boolean,
    val LastName: String,
    val Likes: List<Any>,
    val ProfilePicture: Any,
    val RatingMultiplier: Int,
    val RatingScore: Int
)