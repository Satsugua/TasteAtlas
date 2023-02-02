package com.example.tasteatlas.feature_favorites.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Entity(
    @PrimaryKey val id: Int? = null,
    val entryType: String?,
    val Name: String,
    val timestamp: Long? = null,
    val imageUrl: String? = null
)
class InvalidFavException(msg: String): Exception(msg)