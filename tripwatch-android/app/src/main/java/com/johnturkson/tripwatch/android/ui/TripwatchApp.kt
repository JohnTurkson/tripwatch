package com.johnturkson.tripwatch.android.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.ui.themes.TripwatchTheme

@Composable
fun TripwatchApp(appContainer : AppContainer, navigationViewModel : NavigationViewModel) {
    TripwatchTheme {
        Crossfade(navigationViewModel.currentScreen) { screen ->
            when (screen) {
                is Screen.Launch -> LaunchScreen(appContainer, navigationViewModel)
                is Screen.Home -> HomeScreen(appContainer, navigationViewModel)
            }
        }
    }
}
