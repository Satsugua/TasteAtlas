package com.example.tasteatlas.feature_entity_info.presentation.dish

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasteatlas.feature_entity_info.domain.repository.ItemCommentsRepository
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.comments.CommentEntry
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.where_to_eat.Data
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.where_to_eat.DishRestaurants
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.where_to_eat.WhereToEat
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.where_to_eat.WhereToEatEntry
import com.example.tasteatlas.feature_search.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DishViewModel @Inject constructor(
    private val repository: ItemCommentsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var curPage = 0
    var commentList = mutableStateOf<List<CommentEntry>>(listOf())
    var whereToEatList = mutableStateOf<List<WhereToEatEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    val dishId = savedStateHandle.get<Int>("entityId") ?: -1

    init {
        loadComments()
        loadWhereToEatPaginated()
    }

    fun loadComments() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getCommentsList(dishId)
            when(result) {
                is Resource.Success -> {
                    commentList.value += result.data!!.Data.map{commentItem ->
                        CommentEntry(commentItem.CommentId, commentItem.Content, commentItem.CreatedAt, commentItem.FirstName, commentItem.RatingScore, commentItem.ProfilePicture)
                    }
                    loadError.value = ""
                    isLoading.value = false
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }

    fun loadWhereToEatPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getWhereToEat(dishId, curPage, curPage+5)
            when(result) {
                is Resource.Success -> {
                    // TODO endreached/pagination
                    whereToEatList.value += result.data!!.DishRestaurants.Data.map { item ->
                        WhereToEatEntry(
                            item.Address,
                            item.AggregatedCommentCount,
                            item.AggregatedCriticCommentCount,
                            item.AggregatedScore,
                            item.Category,
                            item.CountryName.uppercase(),
                            item.CountryUrl,
                            item.Critic,
                            item.CriticCount,
                            item.Critics,
                            item.DishRestaurantMultiplier,
                            item.DishUrl,
                            item.Distance,
                            item.Id,
                            item.Image,
                            item.Name,
                            item.RegionEmblemImage,
                            item.RegionId,
                            item.RegionName.uppercase(),
                            item.RegionUrl,
                            item.UrlLink
                        )
                    }
                    curPage+=5
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                }
            }
            isLoading.value = false
        }
    }

    fun addToFav() {

    }
}