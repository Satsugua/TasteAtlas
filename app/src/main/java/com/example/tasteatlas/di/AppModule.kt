package com.example.tasteatlas.di

import android.app.Application
import androidx.room.Room
import com.example.tasteatlas.network.TasteApi
import com.example.tasteatlas.feature_search.domain.repository.ListItemRepository
import com.example.tasteatlas.Constants.BASE_URL
import com.example.tasteatlas.feature_entity_info.domain.repository.ItemCommentsRepository
import com.example.tasteatlas.feature_favorites.data.data_source.FavDatabase
import com.example.tasteatlas.feature_favorites.data.repository.FavRepositoryImpl
import com.example.tasteatlas.feature_favorites.domain.repository.FavRepository
import com.example.tasteatlas.feature_favorites.domain.use_case.AddFav
import com.example.tasteatlas.feature_favorites.domain.use_case.DeleteFav
import com.example.tasteatlas.feature_favorites.domain.use_case.FavUseCases
import com.example.tasteatlas.feature_favorites.domain.use_case.GetFavs
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

    @Singleton
    @Provides
    fun provideFavDatabase(app: Application): FavDatabase {
        return Room.databaseBuilder(
            app,
            FavDatabase::class.java,
            FavDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavRepository(db: FavDatabase): FavRepository {
        return FavRepositoryImpl(db.favDao)
    }

    @Singleton
    @Provides
    fun provideFavUseCases(repository: FavRepository): FavUseCases {
        return FavUseCases(
            getFavs = GetFavs(repository),
            deleteFav = DeleteFav(repository),
            addFav = AddFav(repository)
        )
    }
}