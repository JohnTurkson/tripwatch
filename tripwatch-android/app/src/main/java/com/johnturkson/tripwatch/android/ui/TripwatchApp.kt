package com.johnturkson.tripwatch.android.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.johnturkson.tripwatch.R
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.ui.themes.TripwatchTheme

@Composable
fun TripwatchApp(appContainer : AppContainer, navigationViewModel : NavigationViewModel) {
    TripwatchTheme {
        Crossfade(navigationViewModel.currentScreen) { screen ->
            when (screen) {
                is Screen.Launch -> LaunchScreen(appContainer, navigationViewModel)
                is Screen.Home -> HomeScreen(appContainer, navigationViewModel)
                is Screen.Profile -> ProfileScreen(appContainer, navigationViewModel)
            }
        }
    }
}

@Composable
fun BottomBar(appContainer : AppContainer, selectedTab : TripwatchTab, navigateTo : (Screen) -> Unit) {
    val tabs = TripwatchTab.values()
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(selectedTab) }

    BottomNavigation(modifier = Modifier.preferredHeight(64.dp)) {
        tabs.forEach { tab ->
            BottomNavigationItem(
                icon = { Icon(imageResource(tab.icon)) },
                selected = tab == selectedTab,
                onClick = {
                    if(selectedTab.screen != tab.screen) {
                        if(tab == TripwatchTab.PROFILE)
                            appContainer.profileDisplayUserData = appContainer.userData
                        setSelectedTab(tab)
                        navigateTo (tab.screen)
                    }
                },

                label = { Text(text = stringResource(tab.title)) },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.onPrimary)
        }
    }
}

enum class TripwatchTab(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val screen : Screen
) {
    TRIP_WATCHER(R.string.trip_watcher, R.drawable.mountains_icon, Screen.Launch("")),
    HOME(R.string.home, R.drawable.home_icon, Screen.Home),
    PROFILE(R.string.profile, R.drawable.person_icon, Screen.Profile)
}