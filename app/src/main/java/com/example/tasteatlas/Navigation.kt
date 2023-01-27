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
import com.example.tasteatlas.feature_search.presentation.search_list.SearchListScreen

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
            route = Screen.SearchListScreen.route + "?entryName={entryName}",
            arguments = listOf (
                navArgument(
                name = "entryName"
            ){
//                type = NavType.StringType
                defaultValue = "kebab"
            })) {
            SearchListScreen(navController = navController)
        }
        composable(
            "dish_screen/{entityName}/{entityId}",
             arguments = listOf(
                 navArgument("entityName"){},
                 navArgument("entityId"){
                     type = NavType.IntType
                 }
             )
        ) {
            val entityName = remember {
                it.arguments?.getString("entityName")
            }
            val entityId = remember {
                it.arguments?.getInt("entityId")
            }
            DishScreen(
                entityName = entityName ?: "EntityName",
                entityId = entityId ?: 0,
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