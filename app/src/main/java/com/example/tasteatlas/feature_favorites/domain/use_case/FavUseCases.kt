package com.example.tasteatlas.feature_favorites.domain.use_case

data class FavUseCases (
    val getFavs: GetFavs,
    val deleteFav: DeleteFav,
    val addFav: AddFav,
    val checkIfFav: CheckIfFav
)