package com.example.tasteatlas.feature_search.presentation.search_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasteatlas.feature_favorites.domain.model.Entity
import com.example.tasteatlas.feature_search.domain.model.SearchListEntry
import com.example.tasteatlas.feature_search.domain.repository.ListItemRepository
import com.example.tasteatlas.feature_search.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(
    private val repository: ListItemRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    var searchList = mutableStateOf<List<Entity>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        Timber.tag("TAG").d("STARTED search")
        val argument = savedStateHandle.get<String>("entryName").orEmpty()
        loadSearchList(argument)
    }

    fun loadSearchList(argument: String) {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getSearchList(argument)
            when(result) {
                is Resource.Success -> {
                    searchList.value += result.data!!.CustomItems.mapIndexed { index, customItem ->
                        Entity(customItem.EntityId, customItem.TypeOverride, customItem.Name, null, customItem.PreviewImage.Source)
                    }
                    searchList.value += result.data.Items.mapIndexed { index, item ->
                        Entity(item.EntityId, item.TypeOverride, item.Name, null, item.PreviewImage.Source)
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
}



