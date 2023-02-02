package com.example.tasteatlas.feature_search.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasteatlas.Constants
import com.example.tasteatlas.feature_search.domain.repository.ListItemRepository
import com.example.tasteatlas.feature_search.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ListItemRepository

) : ViewModel() {

    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

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
                    Timber.tag("TAG").d("ERROR LOADING SEARCH LIST")
                    loadError.value = result.message.toString()
                }
            }
            isLoading.value = false
        }
    }
}