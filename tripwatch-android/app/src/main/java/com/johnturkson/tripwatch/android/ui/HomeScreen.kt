package com.johnturkson.tripwatch.android.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.johnturkson.tripwatch.android.data.AppContainer

@Composable
fun HomeScreen(appContainer : AppContainer, navigationViewModel: NavigationViewModel) {
    Text("Hello ${appContainer.userData.email}")
}