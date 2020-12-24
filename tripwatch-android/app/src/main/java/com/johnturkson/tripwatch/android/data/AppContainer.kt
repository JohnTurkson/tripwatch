package com.johnturkson.tripwatch.android.data

import android.content.Context
import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.User
import com.johnturkson.tripwatch.common.data.UserTrip

class AppContainer(private val applicationContext : Context) {
    lateinit var userData : User

    var featuredTripDataList : List<Trip> = emptyList()
    var watchedTripDataList : List<UserTrip> = emptyList()
    var plannedTripDataList : List<UserTrip> = emptyList()
}