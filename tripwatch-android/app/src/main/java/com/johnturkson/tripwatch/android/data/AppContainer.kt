package com.johnturkson.tripwatch.android.data

import android.content.Context
import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.User
import com.johnturkson.tripwatch.common.data.UserTrip

class AppContainer(private val applicationContext : Context) {
    lateinit var userData : User
    lateinit var profileDisplayUserData : User

    lateinit var featuredTripDataList : List<Trip>
    lateinit var watchedTripDataList : List<UserTrip>
    lateinit var plannedTripDataList : List<UserTrip>

}