package com.johnturkson.tripwatch.android.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
        primary = TeaGreen,
        primaryVariant = FernGreen,
        onPrimary = Color.White,
        secondary = BeauBlue,
        secondaryVariant = HonululuBlue,
        onSecondary = Color.White,
        error = Red800
)

private val DarkThemeColors = darkColors(
        primary = TeaGreen,
        primaryVariant = FernGreen,
        onPrimary = Color.Black,
        secondary = BeauBlue,
        onSecondary = Color.White,
        error = Red800
)

@Composable
fun TripwatchTheme(darkTheme : Boolean = isSystemInDarkTheme(), content : @Composable () -> Unit) {
    MaterialTheme(
            colors = if(darkTheme) DarkThemeColors else LightThemeColors,
            content = content)
}