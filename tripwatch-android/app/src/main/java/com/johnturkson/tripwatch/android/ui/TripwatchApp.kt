package com.johnturkson.tripwatch.android.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable

@Composable
fun TripwatchApp(navigationViewModel : NavigationViewModel) {
    Crossfade(navigationViewModel.currentScreen) { screen ->
        when(screen) {
            is Screen.Launch -> LaunchScreen(navigationViewModel)
            is Screen.Home -> HomeScreen(navigationViewModel)
        }
    }
}
