package com.example.tasteatlas

sealed class Screen(val route: String) {
    object DishScreen: Screen("dish_screen")
    object SearchScreen: Screen("search_screen")
    object SearchListScreen: Screen("search_list_screen")
    object FavoriteListScreen: Screen("favorite_list_screen")
}
