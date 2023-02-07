package com.example.tasteatlas.feature_entity_info.presentation.dish

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.example.tasteatlas.components.LoadingPopUp
import com.example.tasteatlas.feature_entity_info.presentation.dish.components.ItemComments
import com.example.tasteatlas.feature_entity_info.presentation.dish.components.ItemWhereToEat
import com.example.tasteatlas.feature_favorites.domain.model.Entity
import com.example.tasteatlas.jsonToData
import com.example.tasteatlas.ui.theme.Roboto
import timber.log.Timber

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DishScreen(
    entityString: String,
    navController: NavController,
    viewModel: DishViewModel = hiltViewModel()
) {

    var checked by remember { viewModel.isFav }
    val entity: Entity = jsonToData(entityString, Entity::class.java) as Entity

    Scaffold(
        topBar = {
            Image(
                painter = rememberImagePainter("https://cdn.tasteatlas.com/images/recipes/bb9742f60c6f44098d09c9493b1b40b3.jpg"),
                contentDescription = "DishImage",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .heightIn(max = 160.dp)
            )
            TopAppBar(
                title = {
                    Text(text = entity.Name)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = Color.Transparent,
                contentColor = Color.White,
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            0f to Color(0x0DFFFFFF),
                            100F to Color(0x66000000)
                        )
                    )
                    .requiredHeight(160.dp),
                actions = {
                    IconToggleButton(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    ) {
                        if (checked) {
                            Icon(Icons.Filled.Favorite, contentDescription = "it is favorite", tint = Color.Red, modifier = Modifier.size(48.dp))
                            viewModel.addToFav()
                        } else {
                            Icon(Icons.Outlined.Favorite, contentDescription = "it is not favorite", modifier = Modifier.size(48.dp))
                            viewModel.removeFromFav()
                        }
                    }
                }
            )
        }, content = {
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Tabs(viewModel)
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

//                    ItemDescription()

                }

            }

        })
}

@Composable
fun Tabs(
    viewModel: DishViewModel
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Overview", "Recipe", "Where To Eat", "Comments")
    Column {
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            edgePadding = 0.dp
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = {
                        Text(text = title)
                    }
                )
            }
        }
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            when (tabIndex) {
                0 -> ItemDescription()
                1 -> ItemRecipe()
                2 -> ItemWhereToEat(viewModel)
                3 -> ItemComments(viewModel)
            }
        }
    }
}

@Composable
fun ItemDescription(
) {
    Text(
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec laoreet interdum ultrices. Mauris quis auctor nisl. Curabitur id quam dui. Ut ultrices sapien nec neque accumsan, eu vestibulum purus mattis. Maecenas nisi odio, ornare at ullamcorper non, aliquet sed lacus. Duis faucibus auctor mi a varius. Donec massa enim, venenatis sed dui vitae, volutpat auctor eros. Maecenas accumsan cursus metus, sed viverra magna fringilla et. Duis vel sollicitudin leo. Curabitur quis ultrices tellus. Maecenas efficitur elit ut nunc vulputate suscipit. Etiam at enim nec leo tempor vehicula. Curabitur lobortis vitae erat vitae cursus. Nullam consectetur ex sed tortor ornare semper. Praesent egestas ut ipsum ac molestie. Quisque ut hendrerit turpis.",
        style = TextStyle(
            fontSize = 15.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Justify,
            color = Color.Black
        )
    )
}

@Composable
fun ItemRecipe(

) {
    Text(
        text = "recipes",
        style = TextStyle(
            fontSize = 15.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Justify,
            color = Color.Black
        )
    )
}


