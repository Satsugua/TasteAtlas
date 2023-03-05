package com.example.tasteatlas.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightPalette = lightColors(
//    primary = Independence,
//    background = PinkLace,
//    onBackground = Thistle2,
//    surface = MediumSlateBlue,
//    onSurface = Thistle
    primary = Color(0xFF990000),
    background = Color.White,
    onBackground = Color(0xFFFDFDFD),
    surface = MediumSlateBlue,
    onSurface = Thistle
)

@Composable
fun TasteAtlasTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightPalette,
        typography = Typography,
        content = content
    )
}