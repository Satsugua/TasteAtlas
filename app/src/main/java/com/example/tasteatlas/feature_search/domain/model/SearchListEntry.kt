package com.example.tasteatlas.feature_search.domain.model

import com.example.tasteatlas.feature_search.data.responses.item.PreviewImage

data class SearchListEntry(
    val entryName: String,
    val entryType: String?,
    val imageUrl: PreviewImage,
    val entryLink: String,
    val entryId: Int
)
