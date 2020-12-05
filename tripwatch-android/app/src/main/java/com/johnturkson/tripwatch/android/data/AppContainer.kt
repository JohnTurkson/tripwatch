package com.johnturkson.tripwatch.android.data

import android.content.Context
import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.User

class AppContainer(private val applicationContext : Context) {
    lateinit var userData : User

    lateinit var tripDataList : List<Trip>
}