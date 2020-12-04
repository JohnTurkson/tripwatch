package com.johnturkson.tripwatch.android.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent

class MainActivity : AppCompatActivity() {

    private val navigationViewModel by viewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripwatchApp(navigationViewModel)
        }
    }
}
