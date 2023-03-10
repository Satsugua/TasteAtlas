package com.example.tasteatlas.feature_entity_info.domain.repository

import com.example.tasteatlas.Constants
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.comments.CommentsList
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.recipe.Recipe
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.where_to_eat.Data
import com.example.tasteatlas.feature_search.util.Resource
import com.example.tasteatlas.network.TasteApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ItemRepository @Inject constructor(
    private val api: TasteApi
){
    suspend fun getCommentsList(argument: Int): Resource<CommentsList> {
        val response = try {
            api.getItemReviews(argument)
        } catch (e: Exception) {
            return Resource.Error("An unknown error.")
        }
        return Resource.Success(response)
    }
    suspend fun getWhereToEat(dishId: Int, start: Int, startTake: Int): Resource<Data> {
        val response = try {
            api.getWhereToEat(dishId, start, startTake)
        } catch (e: Exception) {
        return Resource.Error("Failed to load getWhereToEat")
        }
        return Resource.Success(response)
    }

    suspend fun getRecipe(dishId: Int): Resource<Recipe> {
        val response = try {
            api.getRecipe(dishId)
        } catch (e: Exception) {
            return Resource.Error("Failed to load getRecipe")
        }
        return Resource.Success(response)
    }
}