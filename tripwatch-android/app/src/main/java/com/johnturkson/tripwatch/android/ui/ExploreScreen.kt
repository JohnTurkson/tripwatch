package com.johnturkson.tripwatch.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.johnturkson.tripwatch.R
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.utils.AnimationType
import com.johnturkson.tripwatch.android.utils.URLImage
import com.johnturkson.tripwatch.android.utils.getTripLocation
import com.johnturkson.tripwatch.common.data.Trip

class ExploreViewModel : ViewModel() {
    val searchTextState = mutableStateOf(TextFieldValue())
}

@Composable
fun ExploreScreen(appContainer : AppContainer, navigationViewModel : NavigationViewModel, exploreViewModel: ExploreViewModel) {

    Scaffold(bottomBar = { BottomBar(appContainer, TripwatchTab.EXPLORE, navigationViewModel::navigateTo) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            TripwatchLogoHeader()

            SearchTextBox(
                value = exploreViewModel.searchTextState.value,
                onValueChange = { exploreViewModel.searchTextState.value = it })

            Spacer(Modifier.preferredHeight(16.dp))
            Text(text = "Trips to check out",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.h2)
            Spacer(Modifier.preferredHeight(16.dp))

            ExploreTripList(
                navigateTo = navigationViewModel::navigateTo,
                trips = appContainer.featuredTripDataList,
                modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
private fun SearchTextBox(value : TextFieldValue, onValueChange : (TextFieldValue) -> Unit) {
    Surface(shape = RoundedCornerShape(8.dp)) {
        TextField(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 32.dp),
            value = value,
            onValueChange = onValueChange,
            leadingIcon = { Image(Icons.Filled.Search) })
    }
}

@Composable
private fun ExploreTripList(navigateTo : (Screen) -> Unit, trips : List<Trip>, modifier : Modifier) {
    LazyColumnFor(items = trips, modifier = modifier, itemContent = { trip ->
        Spacer(Modifier.preferredHeight(8.dp))
        Row {
            ExploreImageContainer {
                URLImage(
                    url = trip.imageUrl,
                    enterTransition = AnimationType.SLIDE_HORIZONTALLY,
                    modifier = Modifier.preferredSize(64.dp)
                        .clickable(onClick = {
                            navigateTo(Screen.TripInfo(trip.tripId))
                        })
                )
            }

            Spacer(Modifier.preferredWidth(12.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = trip.tripName,
                    style = MaterialTheme.typography.h2
                )

                Spacer(Modifier.preferredHeight(2.dp))

                Text(
                    text = getTripLocation(trip.tripId),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    })
}

@Composable
private fun ExploreImageContainer(content: @Composable () -> Unit) {
    Surface(Modifier.preferredSize(width = 64.dp, height = 64.dp), RoundedCornerShape(4.dp)) {
        content()
    }
}