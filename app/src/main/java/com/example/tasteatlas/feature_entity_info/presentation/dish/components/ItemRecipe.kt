package com.example.tasteatlas.feature_entity_info.presentation.dish.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasteatlas.feature_entity_info.presentation.dish.DishViewModel
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.recipe.Recipe

@Composable
fun ItemRecipe(
    viewModel: DishViewModel
) {
    val recipeInfo = viewModel.recipeInfo
    if(recipeInfo==null){
        Text(text = "There are currently no recipes", style = MaterialTheme.typography.h1)
    } else ShowContent(recipeInfo)
}

@Composable
fun ShowContent(
    recipeInfo: Recipe
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = recipeInfo.Name,
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(4.dp))
        LazyColumn() {
            items(recipeInfo.Components[0].Ingredients.size) {
                Text(
                    text = recipeInfo.Components[0].Ingredients[it].Description,
                    style = MaterialTheme.typography.h2
                )
            }
        }
    }
}
