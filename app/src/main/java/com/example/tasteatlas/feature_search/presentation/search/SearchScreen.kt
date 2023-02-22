package com.example.tasteatlas.feature_search.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tasteatlas.R
import com.example.tasteatlas.Screen
import com.example.tasteatlas.components.LoadingPopUp
import com.example.tasteatlas.feature_search.presentation.components.DrawerBody
import com.example.tasteatlas.feature_search.presentation.components.DrawerHeader
import com.example.tasteatlas.feature_search.presentation.components.MenuItem
import com.example.tasteatlas.feature_search.presentation.components.SearchItemEntry
import kotlinx.coroutines.launch
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    var text = ""

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isLoading by remember { viewModel.isLoading }
    var searchList by remember { viewModel.searchList }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    if(isLoading) LoadingPopUp()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .clickable (
                interactionSource = interactionSource,
                indication = null    // this gets rid of the ripple effect
            ) {
                focusManager.clearFocus(true)
            },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, "toggle menu drawer")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerBackgroundColor = MaterialTheme.colors.onSurface,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "favorites",
                        title = "Favorites",
                        contentDescription = "Go to favorites",
                        icon = Icons.Filled.Favorite
                    )
                ),
                onItemClick = {
                    navController.navigate(
                        Screen.FavoriteListScreen.route
                    )
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SearchBar(
                modifier = Modifier,
                hint = "Search...",
                onSearch = {
                    text = it
                    if(text.length > 2) viewModel.loadSearchList(text)
                           else searchList = emptyList()
                           },
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn() {
                val itemCount = searchList.size
                items(itemCount) {
                    SearchItemEntry(entry = searchList[it], navController = navController)
                }
            }
        }
    }
}
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {},
    hint: String = ""
) {
    var textInput by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Box(modifier = modifier) {
        BasicTextField(
            value = textInput,
            onValueChange = {
                textInput = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = textInput.isEmpty() && !it.isFocused
                }
        )
        if(isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}