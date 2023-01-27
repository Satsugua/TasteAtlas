package com.example.tasteatlas.feature_favorites.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Entity(
    @PrimaryKey val id: Int? = null,
    val EntityId2: Int? = null,
    val EntityType: Int,
    val Name: String,
    val TypeOverride: String,
    val UrlLink: String,
    val Description: String,
    val timestamp: Long
) {
}