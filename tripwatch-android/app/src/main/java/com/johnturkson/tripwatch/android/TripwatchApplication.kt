package com.johnturkson.tripwatch.android

import android.app.Application
import com.johnturkson.tripwatch.android.data.AppContainer

class TripwatchApplication : Application() {
    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}