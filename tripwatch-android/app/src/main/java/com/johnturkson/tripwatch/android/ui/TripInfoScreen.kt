package com.johnturkson.tripwatch.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.VerticalGradient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.utils.AnimationType
import com.johnturkson.tripwatch.android.utils.ImageGradient
import com.johnturkson.tripwatch.android.utils.URLImage
import com.johnturkson.tripwatch.android.utils.getTripFromId
import com.johnturkson.tripwatch.common.data.Trip

@Composable
fun TripInfoScreen(appContainer : AppContainer, navigationViewModel : NavigationViewModel, selectedTripId : String) {

    Scaffold(bottomBar = { BottomBar(appContainer, TripwatchTab.EXPLORE, navigationViewModel::navigateTo, currentScreen = navigationViewModel.currentScreen) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TripHeaderImage(getTripFromId(selectedTripId))

        }
    }
}

@Composable
private fun TripHeaderImage(selectedTrip : Trip) {
    Box {
        ImageGradient(modifier = Modifier.fillMaxWidth().preferredHeight(256.dp), fadeThreshold = 1024f) {
            URLImage(
                url = selectedTrip.imageUrl,
                enterTransition = AnimationType.SLIDE_VERTICALLY,
                modifier = Modifier.fillMaxWidth()
                    .preferredHeight(256.dp)
            )
        }

        Text(
            text = selectedTrip.tripName,
            modifier = Modifier.align(Alignment.BottomStart).padding(16.dp),
            style = TextStyle(
                color = Color(0xffffffff),
                fontWeight = FontWeight.Bold,
                fontSize=24.sp)
        )
    }
}