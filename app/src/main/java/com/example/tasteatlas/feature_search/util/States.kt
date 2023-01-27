package com.example.tasteatlas.feature_search.util

sealed class States {
    data class Loading(var loading : Boolean) : States()
    data class Error(var errorMsg : String) : States()
}