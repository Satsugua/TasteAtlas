package com.example.tasteatlas.feature_search.presentation.search_list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasteatlas.feature_search.domain.model.SearchListEntry
import com.example.tasteatlas.feature_search.domain.repository.ListItemRepository
import com.example.tasteatlas.feature_search.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(
    private val repository: ListItemRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    var searchList = mutableStateOf<List<SearchListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        Log.d("TAG", "STARTED")
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
                        SearchListEntry(customItem.Name, customItem.TypeOverride, customItem.PreviewImage, customItem.UrlLink, customItem.EntityId)
                    }
                    searchList.value += result.data.Items.mapIndexed { index, item ->
                        SearchListEntry(item.Name, item.TypeOverride, item.PreviewImage, item.UrlLink, item.EntityId)
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



