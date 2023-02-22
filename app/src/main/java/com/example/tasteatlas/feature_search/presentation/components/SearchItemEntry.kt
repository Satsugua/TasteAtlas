package com.example.tasteatlas.feature_search.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.tasteatlas.Constants
import com.example.tasteatlas.dataToJson
import com.example.tasteatlas.feature_favorites.domain.model.Entity
import com.example.tasteatlas.ui.theme.Roboto

@OptIn(ExperimentalCoilApi::class)
@Composable
fun SearchItemEntry(
    entry: Entity,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colors.onBackground)
            .clickable {
                navController.navigate(
                    "dish_screen/${dataToJson(entry)}"
                )
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(80.dp)
        ) {
            if (entry.imageUrl == null) {
                Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = "LocationIcon", modifier = Modifier.size(70.dp), tint = MaterialTheme.colors.primary)
            } else {
                Image(
                    painter = rememberImagePainter(Constants.IMAGE_URL + entry.imageUrl),
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
                    text = entry.Name,
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
