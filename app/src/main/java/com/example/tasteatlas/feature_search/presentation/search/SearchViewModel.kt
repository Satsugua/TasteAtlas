package com.example.tasteatlas.feature_search.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasteatlas.Constants
import com.example.tasteatlas.feature_favorites.domain.model.Entity
import com.example.tasteatlas.feature_search.domain.repository.ListItemRepository
import com.example.tasteatlas.feature_search.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ListItemRepository

) : ViewModel() {

    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var searchList = mutableStateOf<List<Entity>>(listOf())
    var job: Job? = null

    init {
        Timber.tag("TAG").d("CREATED search screen")
        loadAuthentication()
    }
    fun loadAuthentication() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.authentication()
            when (result) {
                is Resource.Success -> {
                    Constants.tokenas += result.data!!.access_token
                    Timber.tag("TAG").d("FINISHED LOADING TOKEN: %s", Constants.tokenas)
                }
                is Resource.Error -> {
                    Timber.tag("TAG").d("ERROR LOADING TOKEN")
                    loadError.value = result.message.toString()
                }
            }
            isLoading.value = false
        }
    }

    fun loadSearchList(argument: String) {
        job?.cancel()
        searchList.value = emptyList()
        job = viewModelScope.launch {
            val result = repository.getSearchList(argument)
            when(result) {
                is Resource.Success -> {
                    searchList.value += result.data!!.CustomItems.map { customItem ->
                        Entity(customItem.EntityId, customItem.TypeOverride, customItem.Name, null, customItem.PreviewImage.Source)
                    }
                    searchList.value += result.data.Items.map { item ->
                        Entity(item.EntityId, item.TypeOverride, item.Name, null, item.PreviewImage.Source)
                    }
                    loadError.value = ""
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                }
            }
        }
    }
}