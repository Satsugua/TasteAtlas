package com.example.tasteatlas.di

import com.example.tasteatlas.network.TasteApi
import com.example.tasteatlas.feature_search.domain.repository.ListItemRepository
import com.example.tasteatlas.Constants.BASE_URL
import com.example.tasteatlas.feature_entity_info.domain.repository.ItemCommentsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSearchListRepository(
        api: TasteApi
    ) = ListItemRepository(api)

    @Singleton
    @Provides
    fun provideCommentsRepository(
        api: TasteApi
    ) = ItemCommentsRepository(api)

    @Singleton
    @Provides
    fun provideTateApi(): TasteApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(TasteApi::class.java)
    }
}