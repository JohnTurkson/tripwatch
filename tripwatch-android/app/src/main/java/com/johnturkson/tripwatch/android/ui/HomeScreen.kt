package com.johnturkson.tripwatch.android.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.johnturkson.tripwatch.R
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.utils.getFeaturedTripsForUser
import com.johnturkson.tripwatch.android.utils.getPlannedTripsFromUserId
import com.johnturkson.tripwatch.android.utils.getWatchedTripsFromUserId
import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.User
import com.johnturkson.tripwatch.common.data.UserTrip

@Composable
fun HomeScreen(appContainer : AppContainer, navigationViewModel: NavigationViewModel) {

    // TODO remove, for debug
    appContainer.userData = User("12345678", "solarcactus@live.com")

    appContainer.featuredTripDataList = getFeaturedTripsForUser(appContainer.userData.id)
    appContainer.watchedTripDataList = getWatchedTripsFromUserId(appContainer.userData.id)
    appContainer.plannedTripDataList = getPlannedTripsFromUserId(appContainer.userData.id)

    Scaffold(bottomBar = { BottomBar(navigationViewModel::navigateTo) }) { innerPadding ->
        ScrollableColumn(modifier = Modifier.padding(innerPadding).fillMaxWidth()) {
            Column {

                Spacer(modifier = Modifier.preferredHeight(16.dp))
                Text(
                    text = "Trips to check out",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h2
                )

                FeaturedTripCards(trips = appContainer.featuredTripDataList,
                    navigateTo = navigationViewModel::navigateTo,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .preferredWidth(384.dp).preferredHeight(320.dp))

                Text(
                    text = "Trips you're watching",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.h2
                )

                UserTripCardDisplay(trips = appContainer.watchedTripDataList,
                    navigateTo = navigationViewModel::navigateTo,
                    modifier = Modifier.preferredWidth(256.dp).preferredHeight(192.dp))

                Text(
                    text = "Trips you've planned",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.h2
                )

                UserTripCardDisplay(trips = appContainer.plannedTripDataList,
                    navigateTo = navigationViewModel::navigateTo,
                    modifier = Modifier.preferredWidth(256.dp).preferredHeight(192.dp))
            }
        }
    }
}

@Composable
fun FeaturedTripCards(trips : List<Trip>, navigateTo : (Screen) -> Unit, modifier : Modifier) {
    TripCard(trips[0], modifier = modifier.fillMaxWidth(), onClick = {
        navigateTo(Screen.Home)
    })
}

@Composable
fun UserTripCardDisplay(trips : List<UserTrip>, navigateTo : (Screen) -> Unit, modifier : Modifier) {
    ScrollableRow {
        Row {
            trips.forEach { trip ->
                UserTripCard(
                    userTripData = trip,
                    modifier = modifier,
                    onClick = {
                        navigateTo(Screen.Home)
                })
            }
        }
    }
}

@Composable
fun BottomBar(navigateTo : (Screen) -> Unit) {
    val tabs = HomeTabs.values()
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(HomeTabs.HOME) }

    BottomNavigation(modifier = Modifier.preferredHeight(64.dp)) {
        tabs.forEach { tab ->
            BottomNavigationItem(
                icon = { Icon(imageResource(tab.icon)) },
                selected = tab == selectedTab,
                onClick = {
                            if(selectedTab.screen != tab.screen) {
                                setSelectedTab(tab)
                                navigateTo(tab.screen)
                            }
                          },

                label = { Text(text = stringResource(tab.title)) },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.onPrimary)
        }
    }
}

enum class HomeTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val screen : Screen
) {
    TRIP_WATCHER(R.string.trip_watcher, R.drawable.mountains_icon, Screen.Launch("")),
    HOME(R.string.home, R.drawable.home_icon, Screen.Home),
    PROFILE(R.string.profile, R.drawable.person_icon, Screen.Launch(""))
}