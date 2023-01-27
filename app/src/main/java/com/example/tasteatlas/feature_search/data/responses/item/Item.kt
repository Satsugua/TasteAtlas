package com.example.tasteatlas.feature_search.data.responses.item

data class Item(
    val EntityId: Int,
    val EntityId2: Any,
    val EntityType: Int,
    val Name: String,
    val OtherName: Any,
    val PreviewImage: PreviewImage,
    val Subtitle: Any,
    val TypeOverride: String?,
    val UrlLink: String
)