package com.johnturkson.tripwatch.android.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.johnturkson.tripwatch.android.TripwatchApplication

class MainActivity : AppCompatActivity() {

    private val navigationViewModel by viewModels<NavigationViewModel>()
    private val homeViewModel by viewModels<HomeViewModel>()
    private val exploreViewModel by viewModels<ExploreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appContainer = (application as TripwatchApplication).container;
            TripwatchApp(appContainer, navigationViewModel, homeViewModel, exploreViewModel)
        }
    }
}
