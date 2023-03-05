package com.example.tasteatlas.feature_entity_info.presentation.dish.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasteatlas.feature_entity_info.presentation.dish.DishViewModel
import com.example.tasteatlas.feature_entity_info.presentation.dish.model.comments.CommentEntry
import com.example.tasteatlas.ui.theme.Roboto

@Composable
fun ItemComments(
    viewModel: DishViewModel
) {
    val commentList by remember { viewModel.commentList }
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(commentList.size) {
            CommentListEntry(entry = commentList[it])
        }
    }
}

@Composable
fun CommentListEntry(
    entry: CommentEntry
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp, RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colors.onBackground)

    ) {
        Column {
            Row {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Account icon",
                    modifier = Modifier.size(50.dp),
                    tint = MaterialTheme.colors.primary
                )
                Column {
                    Text(
                        text = entry.commentFirstName,
                        style = MaterialTheme.typography.h1
                    )
                    Row {
                        StarRating(entry.commentRating)
                        //TODO DATE
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = entry.commentContent,
                    style = MaterialTheme.typography.h2
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun StarRating(
    rating: Int
) {
    for ( i in 0..4 ) {
        if( rating-i*2 > 1 )
            Icon(Icons.Filled.Star , contentDescription = "rating star", tint = MaterialTheme.colors.primary)
        else if ( rating-i*2 == 1 )
            Icon(Icons.Filled.StarHalf , contentDescription = "rating star", tint = MaterialTheme.colors.primary)
        else
            Icon(Icons.Outlined.StarBorder , contentDescription = "rating star", tint = MaterialTheme.colors.primary)
    }
}