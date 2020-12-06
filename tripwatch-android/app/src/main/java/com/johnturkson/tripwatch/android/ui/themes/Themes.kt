package com.johnturkson.tripwatch.android.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
        primary = AvocadoGreen,
        primaryVariant = FernGreen,
        onPrimary = Color.White,
        secondary = BeauBlue,
        secondaryVariant = HonululuBlue,
        onSecondary = CulturedWhite,
        error = Red800
)

private val DarkThemeColors = darkColors(
        primary = AvocadoGreen,
        primaryVariant = FernGreen,
        onPrimary = Color.Black,
        secondary = BeauBlue,
        onSecondary = CulturedWhite,
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