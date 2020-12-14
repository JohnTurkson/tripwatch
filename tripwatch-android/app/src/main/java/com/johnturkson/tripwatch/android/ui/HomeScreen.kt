package com.johnturkson.tripwatch.android.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
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
import com.johnturkson.tripwatch.android.utils.pictureCache
import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.User
import com.johnturkson.tripwatch.common.data.UserTrip

@Composable
fun HomeScreen(appContainer : AppContainer, navigationViewModel: NavigationViewModel) {
    // TODO remove, for debug
    appContainer.userData = FakeUserData

    appContainer.featuredTripDataList = getFeaturedTripsForUser(appContainer.userData.id)
    appContainer.watchedTripDataList = getWatchedTripsFromUserId(appContainer.userData.id)
    appContainer.plannedTripDataList = getPlannedTripsFromUserId(appContainer.userData.id)

    Scaffold(bottomBar = { BottomBar(appContainer, TripwatchTab.HOME, navigationViewModel::navigateTo) }) { innerPadding ->
        ScrollableColumn(modifier = Modifier.padding(innerPadding).fillMaxWidth()) {
            Column {
                Image(bitmap = imageResource(R.drawable.trip_watch),
                    modifier = Modifier.align(Alignment.Start).padding(horizontal = 16.dp).preferredWidth(128.dp).preferredHeight(48.dp))

                Spacer(modifier = Modifier.preferredHeight(16.dp))
                Text(
                    text = "Trips to check out",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h1
                )

                FeaturedTripCards(trips = appContainer.featuredTripDataList,
                    navigateTo = navigationViewModel::navigateTo,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .preferredWidth(384.dp).preferredHeight(288.dp))

                Text(
                    text = "Trips you're watching",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.h2
                )

                UserTripCardDisplay(trips = appContainer.watchedTripDataList,
                    appContainer = appContainer,
                    navigateTo = navigationViewModel::navigateTo,
                    modifier = Modifier.preferredWidth(256.dp).preferredHeight(192.dp))

                Text(
                    text = "Trips you've planned",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.h2
                )

                UserTripCardDisplay(trips = appContainer.plannedTripDataList,
                    appContainer = appContainer,
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
fun UserTripCardDisplay(trips : List<UserTrip>, appContainer: AppContainer, navigateTo : (Screen) -> Unit, modifier : Modifier) {
    LazyRowFor(items = trips, itemContent = { trip ->
            UserTripCard(
                userTripData = trip,
                modifier = modifier,
                appContainer = appContainer,
                navigateTo = navigateTo)
        })
}