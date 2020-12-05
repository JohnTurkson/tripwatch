package com.johnturkson.tripwatch.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.common.data.Trip

@Composable
fun HomeScreen(appContainer : AppContainer, navigationViewModel: NavigationViewModel) {
    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).preferredHeight(200.dp)) {
        TripImage(Trip("Seymour", emptyList(), "https://lynncanyon.ca/assets/mount-seymour-baker-view.jpg"))
    }
}