package com.example.tasteatlas.feature_favorites.domain.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasteatlas.feature_favorites.domain.components.DefaultRadioButton
import com.example.tasteatlas.feature_favorites.domain.util.FavOrder
import com.example.tasteatlas.feature_favorites.domain.util.OrderType

@Composable
fun Ordering(
    modifier: Modifier = Modifier,
    favOrder: FavOrder = FavOrder.Date(OrderType.Descending),
    onOrderChange: (FavOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = favOrder is FavOrder.Title,
                onSelect = { onOrderChange(FavOrder.Title(favOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = favOrder is FavOrder.Date,
                onSelect = { onOrderChange(FavOrder.Date(favOrder.orderType)) }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = favOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(favOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = favOrder is FavOrder.Date,
                onSelect = {
                    onOrderChange(favOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}