package com.johnturkson.tripwatch.android.ui

import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.johnturkson.tripwatch.R
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.utils.*
import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.UserTrip
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class HomeViewModel : ViewModel() {}

@Composable
fun HomeScreen(appContainer : AppContainer, navigationViewModel: NavigationViewModel, homeViewModel: HomeViewModel) {
    // TODO remove, for debug
    appContainer.userData = FakeUserData

    appContainer.featuredTripDataList = getFeaturedTripsForUser(appContainer.userData.id)
    appContainer.watchedTripDataList = getWatchedTripsFromUserId(appContainer.userData.id)
    appContainer.plannedTripDataList = getPlannedTripsFromUserId(appContainer.userData.id)

    Scaffold(bottomBar = { BottomBar(appContainer, TripwatchTab.HOME, navigationViewModel::navigateTo) }) { innerPadding ->
        ScrollableColumn(modifier = Modifier.padding(innerPadding).fillMaxWidth()) {
            Column {

                TripwatchLogoHeader()

                AnimatedText(
                    text = "Trips to check out",
                    enterTransition = AnimationType.SLIDE_VERTICALLY,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.h1,
                    visible = true
                )

                FeaturedTripCards(trips = appContainer.featuredTripDataList,
                    navigateTo = navigationViewModel::navigateTo,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .preferredWidth(384.dp).preferredHeight(288.dp))

                AnimatedText(
                    text = "Trips you're watching",
                    enterTransition = AnimationType.SLIDE_VERTICALLY,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.h2,
                    visible = true
                )

                UserTripCardDisplay(trips = appContainer.watchedTripDataList,
                    appContainer = appContainer,
                    navigateTo = navigationViewModel::navigateTo,
                    modifier = Modifier.preferredWidth(256.dp).preferredHeight(192.dp))

                AnimatedText(
                    text = "Trips you've planned",
                    enterTransition = AnimationType.SLIDE_VERTICALLY,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.h2,
                    visible = true
                )

                UserTripCardDisplay(trips = appContainer.plannedTripDataList,
                    appContainer = appContainer,
                    navigateTo = navigationViewModel::navigateTo,
                    modifier = Modifier.preferredWidth(256.dp).preferredHeight(192.dp))
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedText(text : String, enterTransition : AnimationType, modifier : Modifier, style : TextStyle, visible : Boolean) {
    AnimatedVisibility(visible = visible, enter = enterTransition.transition) {
        Text(text = text, modifier = modifier, style = style)
    }
}

@Composable
fun FeaturedTripCards(trips : List<Trip>, navigateTo : (Screen) -> Unit, modifier : Modifier) {
    TripCard(trips[0],
        modifier = modifier.fillMaxWidth(),
        navigateTo = navigateTo
    )
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