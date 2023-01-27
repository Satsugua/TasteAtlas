package com.example.tasteatlas.network

import com.example.tasteatlas.Constants.tokenas
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.comments.CommentsList
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.where_to_eat.Data
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.where_to_eat.DishRestaurants
import com.example.tasteatlas.feature_login.data.tokenResponse.TokenInfo
import com.example.tasteatlas.feature_search.data.responses.item.ListListItems
import retrofit2.http.*

interface TasteApi {

    @GET("/api/v3/autocomplete")
    suspend fun getListResult(
        @Query("query") value: String,
        @Header("AUTHORIZATION") token: String = tokenas
    ) : ListListItems

    @POST("/account/Anonymous")
    suspend fun getToken() : TokenInfo

    @GET("api/v2/dishes/{dish_id}/review")
    suspend fun getItemReviews(
        @Path(value = "dish_id") dishId: Int,
        @Header("AUTHORIZATION") token: String
    ) : CommentsList

    @GET("api/v2/dishes/{dish_id}/restaurants")
    suspend fun getWhereToEat(
        @Path(value = "dish_id") dishId: Int,
        @Query("Start") start: Int,
        @Query("StartTake") startTake: Int,
        @Header("AUTHORIZATION") token: String = tokenas
    ) : Data

}