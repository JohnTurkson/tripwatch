package com.johnturkson.tripwatch.android.utils

import com.johnturkson.tripwatch.android.ui.*
import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.User
import com.johnturkson.tripwatch.common.data.UserTrip

const val CURRENT_USER_ID_KEY = "CURRENT_USER"

/*
* File for anything communicating with the backend endpoint
* that retrieves user data (friends, trips, profile picture, etc...)
*
*/
fun getFeaturedTripsForUser(userId : String) : List<Trip> {
    return FakeTrips.map { pair -> pair.value }
}

fun getPlannedTripsFromUserId(userId : String) : List<UserTrip> {
    // TODO actual backend stuff
    return FakePlannedUserTrips
}

fun getWatchedTripsFromUserId(userId : String) : List<UserTrip> {
    // TODO actual backend stuff
    return FakeWatchedUserTrips
}

fun getUserProfilePictureUrl(userId : String) : String? {
    //TODO actual backend stuff
    return FakeUserProfilePictures[userId]
}

fun getUserDataFromId(userId : String)  : User {

    // TODO actual backend stuff
    FakeUsers.forEach { user ->
        if(user.id == userId) return user
    }

    return User("", "")
}