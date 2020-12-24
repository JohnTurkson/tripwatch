package com.johnturkson.tripwatch.android.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.johnturkson.tripwatch.R
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.ui.themes.TripwatchTheme
import com.johnturkson.tripwatch.android.utils.CURRENT_USER_ID_KEY
import com.johnturkson.tripwatch.android.utils.pictureCache

@Composable
fun TripwatchApp(appContainer : AppContainer, navigationViewModel : NavigationViewModel, homeViewModel: HomeViewModel, exploreViewModel: ExploreViewModel) {
    pictureCache.clear()
    TripwatchTheme {

        Crossfade(navigationViewModel.currentScreen) { screen ->
            when (screen) {
                is Screen.Launch -> LaunchScreen(appContainer, navigationViewModel)
                is Screen.Home -> HomeScreen(appContainer, navigationViewModel, homeViewModel)
                is Screen.Profile -> ProfileScreen(appContainer, navigationViewModel, screen.userId)
                is Screen.Explore -> ExploreScreen(appContainer, navigationViewModel, exploreViewModel)
                is Screen.TripInfo -> TripInfoScreen(appContainer, navigationViewModel, screen.tripId)
            }
        }
    }
}

@Composable
fun TripwatchLogoHeader() {
    Box {
        Image(
            bitmap = imageResource(R.drawable.trip_watch),
            modifier = Modifier.align(Alignment.TopStart).padding(horizontal = 16.dp)
                .preferredWidth(128.dp).preferredHeight(48.dp)
        )

        Spacer(modifier = Modifier.preferredHeight(16.dp))
    }
}

@Composable
fun BottomBar(appContainer : AppContainer, selectedTab : TripwatchTab, navigateTo : (Screen) -> Unit, currentScreen : Screen? = selectedTab.screen) {
    val tabs = TripwatchTab.values()
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(selectedTab) }

    BottomNavigation(modifier = Modifier.preferredHeight(64.dp)) {
        tabs.forEach { tab ->
            BottomNavigationItem(
                icon = { Icon(vectorResource(tab.icon)) },
                selected = tab == selectedTab,
                onClick = {
                    if(selectedTab.screen != tab.screen || currentScreen != tab.screen) {
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
    EXPLORE(R.string.explore, R.drawable.ic_mountain, Screen.Explore),
    PROFILE(R.string.profile, R.drawable.ic_baseline_person_24, Screen.Profile(CURRENT_USER_ID_KEY))
}