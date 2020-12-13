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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.johnturkson.tripwatch.R
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.ui.themes.TripwatchTheme
import com.johnturkson.tripwatch.android.utils.pictureCache

@Composable
fun TripwatchApp(appContainer : AppContainer, navigationViewModel : NavigationViewModel) {
    pictureCache.clear()
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
                icon = { Icon(vectorResource(tab.icon)) },
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
                unselectedContentColor = Color.White)
        }
    }
}

enum class TripwatchTab(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val screen : Screen
) {
    HOME(R.string.home, R.drawable.ic_baseline_home_24, Screen.Home),
    TRIP_WATCHER(R.string.trip_watcher, R.drawable.ic_mountain, Screen.Launch("")),
    PROFILE(R.string.profile, R.drawable.ic_baseline_person_24, Screen.Profile)
}