package com.example.tasteatlas.feature_search.domain.repository

import com.example.tasteatlas.feature_login.data.tokenResponse.TokenInfo
import com.example.tasteatlas.network.TasteApi
import com.example.tasteatlas.feature_search.data.responses.item.ListListItems
import com.example.tasteatlas.Constants.tokenas
import com.example.tasteatlas.feature_search.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ListItemRepository @Inject constructor(
    private val api: TasteApi
){
    suspend fun getSearchList(argument: String): Resource<ListListItems> {
        val response = try {
            api.getListResult(argument)
        } catch (e: Exception) {
            return Resource.Error("An unknown error.")
        }
        return Resource.Success(response)
    }

    suspend fun authentication() : Resource<TokenInfo> {
        val response = try {
            api.getToken()
        } catch (e: Exception) {
            return Resource.Error("Authentication error.")
        }
        return Resource.Success(response)
    }
}