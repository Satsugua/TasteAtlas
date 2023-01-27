package com.example.tasteatlas.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.tasteatlas.R

val Roboto = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold),
)

val Typography = androidx.compose.material.Typography(
    body1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h2 = TextStyle(
        fontSize = 14.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Left,
        color = Color.Black
    ),
    h1 = TextStyle(
        fontSize = 16.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Left,
        color = Color.Black
    ),
    body2 = TextStyle(
        fontSize = 14.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Left,
        color = Color.DarkGray
    )
)