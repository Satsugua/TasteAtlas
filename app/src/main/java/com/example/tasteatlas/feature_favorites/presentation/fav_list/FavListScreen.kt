package com.example.tasteatlas.feature_favorites.presentation.fav_list

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tasteatlas.feature_favorites.domain.components.Ordering
import com.example.tasteatlas.feature_search.presentation.components.SearchItemEntry

@Composable
fun FavListScreen(
    navController: NavController,
    viewModel: FavListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Favorites")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "back button")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp,
                actions = {
                    IconButton(onClick = {
                        viewModel.onEvent(FavEvents.ToggleOrder)
                    }
                    ) {
                        Icon(Icons.Outlined.FilterAlt, "filter button")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Ordering(onOrderChange = {
                    viewModel.onEvent(FavEvents.Order(it))
                })
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
            ) {

                items(state.favs) { fav ->
                    SearchItemEntry(entry = fav, navController = navController)
                }
            }
        }
    }
}
