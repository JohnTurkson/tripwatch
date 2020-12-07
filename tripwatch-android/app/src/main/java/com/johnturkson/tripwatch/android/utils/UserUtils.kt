package com.johnturkson.tripwatch.android.utils

import com.johnturkson.tripwatch.android.ui.FakePlannedUserTrips
import com.johnturkson.tripwatch.android.ui.FakeTrips
import com.johnturkson.tripwatch.android.ui.FakeWatchedUserTrips
import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.UserTrip

/*
* File for anything communicating with the backend endpoint
* that retrieves user data (friends, trips, profile picture, etc...)
*
*/
fun getFeaturedTripsForUser(userId : String) : List<Trip> {
    return FakeTrips
}

fun getPlannedTripsFromUserId(userId : String) : List<UserTrip> {
    // TODO actual backend stuff
    return FakePlannedUserTrips
}

fun getWatchedTripsFromUserId(userId : String) : List<UserTrip> {
    // TODO actual backend stuff
    return FakeWatchedUserTrips
}

fun getUserProfilePictureUrl(userId : String) : String {
    //TODO actual backend stuff
    return ""
}