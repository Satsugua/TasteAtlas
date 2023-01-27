package com.example.tasteatlas.feature_favorites.presentation.fav_list

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.tasteatlas.Constants
import com.example.tasteatlas.feature_search.domain.model.SearchListEntry
import com.example.tasteatlas.feature_search.presentation.search_list.SearchListViewModel
import com.example.tasteatlas.ui.theme.Roboto


@Composable
fun FavListScreen(
    navController: NavController
) {
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
//                    .padding(16.dp)
        ) {

        }

    }
}

@Composable
fun FavItemList(
    navController: NavController,
    viewModel: SearchListViewModel = hiltViewModel(),
) {
    val searchList by remember { viewModel.searchList }
    val loadError by remember { viewModel.loadError }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = searchList.size
        Log.d("TAG", "listsize: " + searchList.size)
        items(itemCount) {
            com.example.tasteatlas.feature_search.presentation.search_list.SearchItemEntry(entry = searchList[it], navController = navController)
        }
    }

}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun FavItemEntry(
    entry: SearchListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
//    viewModel: SearchListViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colors.onBackground)
            .clickable {
                navController.navigate(
                    "dish_screen/${entry.entryName}/${entry.entryId}"
                )
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(80.dp)
        ) {
            if (entry.imageUrl.Image == null) {
                Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = "LocationIcon", modifier = Modifier.size(70.dp), tint = MaterialTheme.colors.primary)
            } else {
                Image(
                    painter = rememberImagePainter(Constants.IMAGE_URL + entry.imageUrl.Image),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = entry.entryName,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Left,
                        color = Color.Black
                    )
                )
                Text(
                    text = entry.entryType ?: "",
                    style = TextStyle(
                        fontSize = 15.sp,
                        textAlign = TextAlign.Left,
                        color = Color.DarkGray
                    )
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}