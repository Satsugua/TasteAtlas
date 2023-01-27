package com.example.tasteatlas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasteatlas.feature_search.domain.repository.ListItemRepository
import com.example.tasteatlas.ui.theme.TasteAtlasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasteAtlasTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ){
                    Navigation()
                }
            }
        }
    }
}


