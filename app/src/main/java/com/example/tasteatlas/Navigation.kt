package com.example.tasteatlas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tasteatlas.feature_entity_info.presentation.dish.DishScreen
import com.example.tasteatlas.feature_favorites.presentation.fav_list.FavListScreen
import com.example.tasteatlas.feature_search.presentation.search.SearchScreen
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SearchScreen.route
    ){
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(
            "dish_screen/{entityString}",
             arguments = listOf(
                 navArgument("entityString"){}
             )
        ) {
            val entity = remember {
                it.arguments?.getString("entityString")
            }
            DishScreen(
                entityString = entity ?: "",
                navController = navController
            )
        }
        composable(
            route = Screen.FavoriteListScreen.route
        ) {
            FavListScreen(navController = navController)
        }
    }
}