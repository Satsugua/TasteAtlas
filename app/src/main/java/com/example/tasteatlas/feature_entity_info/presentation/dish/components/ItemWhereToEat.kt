package com.example.tasteatlas.feature_entity_info.presentation.dish.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.tasteatlas.Constants
import com.example.tasteatlas.feature_entity_info.presentation.dish.DishViewModel
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.where_to_eat.WhereToEatEntry

@Composable
fun ItemWhereToEat(
    viewModel: DishViewModel
) {
    val whereToEatList by remember { viewModel.whereToEatList }
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(whereToEatList.size) {
            ItemWhereToEatEntry(entry = whereToEatList[it], viewModel)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ItemWhereToEatEntry(
    entry: WhereToEatEntry,
    viewModel: DishViewModel
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(5.dp, RoundedCornerShape(5.dp), true)
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colors.onBackground)
            .clickable {
                viewModel.openRestaurantMap(context = context, address = entry.Name + ", " + entry.Address)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = rememberImagePainter(Constants.IMAGE_URL + entry.Image),
                contentDescription = "Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .heightIn(max = 160.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = entry.Name,
                style = MaterialTheme.typography.h1
            )
            Text(
                text = entry.RegionName + ", " + entry.CountryName,
                style = MaterialTheme.typography.h2
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}