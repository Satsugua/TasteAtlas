package com.example.tasteatlas.feature_search.presentation.search

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasteatlas.Constants
import com.example.tasteatlas.feature_search.domain.repository.ListItemRepository
import com.example.tasteatlas.feature_search.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ListItemRepository

) : ViewModel() {

    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        Log.d("TAG", "SUKURTA")
        loadAuthentication()
    }

    fun loadAuthentication() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.authentication()
            when (result) {
                is Resource.Success -> {
                    Constants.tokenas += result.data!!.access_token
                    Log.d("TAG", "FINISHED LOADING, tokenas: " + Constants.tokenas)
                }
                is Resource.Error -> {
                    Log.d("TAG", "ERROR LOADING")
                    loadError.value = result.message.toString()
                }
            }
            isLoading.value = false
        }
    }
}