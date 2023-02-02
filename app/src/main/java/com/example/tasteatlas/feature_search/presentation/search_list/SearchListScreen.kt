package com.example.tasteatlas.feature_search.presentation.search_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tasteatlas.feature_search.presentation.components.SearchItemEntry
import timber.log.Timber
@Composable
fun SearchListScreen(
    navController: NavController
) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Name of list")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    elevation = 10.dp
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                SearchItemList(navController = navController)
            }
        }
}
@Composable
fun SearchItemList(
    navController: NavController,
    viewModel: SearchListViewModel = hiltViewModel(),
) {
    val searchList by remember { viewModel.searchList }
    val loadError by remember { viewModel.loadError }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = searchList.size
        Timber.tag("TAG").d("listsize: %s", searchList.size)
        items(itemCount) {
            SearchItemEntry(entry = searchList[it], navController = navController)
        }
    }
}

