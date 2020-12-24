package com.johnturkson.tripwatch.android.utils

import com.johnturkson.tripwatch.android.ui.FakeTrips
import com.johnturkson.tripwatch.android.ui.FakeTripLocations
import com.johnturkson.tripwatch.common.data.Trip


// TODO actual backend/API call
fun getTripLocation(tripId : String) : String {
    return FakeTripLocations[tripId] ?: error("Can't find trip location")
}

// TODO actual backend call
fun getTripFromId(tripName : String) : Trip {
    return FakeTrips[tripName] ?: error("Can't find trip")
}