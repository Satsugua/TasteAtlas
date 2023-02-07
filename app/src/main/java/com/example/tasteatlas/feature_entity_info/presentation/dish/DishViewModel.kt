package com.example.tasteatlas.feature_entity_info.presentation.dish

import android.util.Log
import androidx.compose.material.Snackbar
import androidx.compose.material.rememberScaffoldState
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
import com.example.tasteatlas.feature_favorites.domain.model.Entity
import com.example.tasteatlas.feature_favorites.domain.model.InvalidFavException
import com.example.tasteatlas.feature_favorites.domain.use_case.FavUseCases
import com.example.tasteatlas.feature_favorites.domain.util.FavOrder
import com.example.tasteatlas.feature_search.util.Resource
import com.example.tasteatlas.jsonToData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DishViewModel @Inject constructor(
    private val repository: ItemCommentsRepository,
    private val favUseCases: FavUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var curPage = 0
    var commentList = mutableStateOf<List<CommentEntry>>(listOf())
    var whereToEatList = mutableStateOf<List<WhereToEatEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var isLoadingFav = mutableStateOf(false)
    val entity: Entity = jsonToData(savedStateHandle.get("entityString")!!, Entity::class.java) as Entity
    var isFav = mutableStateOf(false)
    init {

        loadComments()
        loadWhereToEatPaginated()
        isFavChecked()
    }

    fun loadComments() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getCommentsList(entity.id!!)
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
            val result = repository.getWhereToEat(entity.id!!, curPage, curPage+5)
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

    private fun isFavChecked(){
        var temp: Boolean
        CoroutineScope(Dispatchers.IO).launch {
            try {
                temp = favUseCases.checkIfFav(entity.id!!)
                withContext(Dispatchers.Main) {
                    isFav.value = temp
                }
            } catch (e: Exception) {
                Timber.tag("TAG").d("ERROR CHECKING IF FAV: %s", e)
            }
        }
    }

    fun addToFav() {
        viewModelScope.launch {
            try {
                favUseCases.addFav(entity)
            } catch (e: InvalidFavException) {
                Timber.tag("TAG").d("ERROR ADDING TO FAVS: %s", e)
            }
        }
    }

    fun removeFromFav() {
        viewModelScope.launch {
            try {
                favUseCases.deleteFav(entity)
            } catch (e: Exception) {
                Timber.tag("TAG").d("ERROR REMOVING FAV: %s", e)
            }
        }
    }
}