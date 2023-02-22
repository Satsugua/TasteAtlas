package com.example.tasteatlas.feature_favorites.data.data_source

import androidx.room.*
import com.example.tasteatlas.feature_favorites.domain.model.Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavDao {
    @Query("SELECT * FROM Entity")
    fun getFavs() : Flow<List<Entity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFav(entity: Entity)
    @Delete
    suspend fun deleteFav(entity: Entity)
    @Query("SELECT * FROM Entity WHERE id = :id")
    fun checkIfFav(id: Int): Boolean
}