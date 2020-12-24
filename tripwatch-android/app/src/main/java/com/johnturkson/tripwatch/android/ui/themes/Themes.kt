package com.johnturkson.tripwatch.android.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.VerticalGradient

private val LightThemeColors = lightColors(
        primary = SeaGreen,
        primaryVariant = Color.White,
        onPrimary = Color.Black,
        secondary = HoneyYellow,
        secondaryVariant = Purple,
        onSecondary = Color.White,
        error = Red800
)

private val DarkThemeColors = darkColors(
        primary = Color.Black,
        primaryVariant = SeaGreen,
        onPrimary = HoneyYellow,
        secondary = Purple,
        onSecondary = Color.Black,
        error = Red800
)

@Composable
fun TripwatchTheme(darkTheme : Boolean = isSystemInDarkTheme(), content : @Composable () -> Unit) {
    MaterialTheme(
            colors = if(darkTheme) DarkThemeColors else LightThemeColors,
            shapes = TripwatchShapes,
            typography = TripwatchTypography,
            content = content)
}