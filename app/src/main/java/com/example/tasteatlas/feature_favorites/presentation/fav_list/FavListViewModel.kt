package com.example.tasteatlas.feature_favorites.presentation.fav_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasteatlas.dataToJson
import com.example.tasteatlas.feature_favorites.domain.model.Entity
import com.example.tasteatlas.feature_favorites.domain.use_case.FavUseCases
import com.example.tasteatlas.feature_favorites.domain.util.FavOrder
import com.example.tasteatlas.feature_favorites.domain.util.OrderType
import com.example.tasteatlas.jsonToData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavListViewModel @Inject constructor(
    private val favUseCases: FavUseCases

) : ViewModel() {

    private val _state = mutableStateOf(FavStates())
    val state: State<FavStates> = _state
    private var recentlyDeleted: Entity? = null
    private var getFavJob: Job? = null
    init {
        Timber.tag("TAG").d("FAV SCREEN OPENING")
        getFavs(FavOrder.Date(OrderType.Descending))
    }
    private fun getFavs(favOrder: FavOrder) {
        getFavJob?.cancel()
        getFavJob = favUseCases.getFavs(favOrder)
            .onEach { favs ->
                _state.value = state.value.copy(
                    favs = favs,
                    favOrder = favOrder
                )
            }.launchIn(viewModelScope)
    }
}