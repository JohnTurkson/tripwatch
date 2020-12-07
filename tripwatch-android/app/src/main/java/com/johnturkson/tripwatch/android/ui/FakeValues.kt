package com.johnturkson.tripwatch.android.ui

import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.User
import com.johnturkson.tripwatch.common.data.UserTrip

val LindemanLake = Trip("Lindeman Lake", "https://www.vancouvertrails.com/images/photos/lindeman-lake-5.jpg")
val EvansPeak = Trip( "Evans Peak", "https://jelgerandtanja.com/wp-content/uploads/2018/10/Evans-Peak-hike.jpg")
val GoldCreek = Trip( "Gold Creek", "https://www.outdoorproject.com/sites/default/files/styles/hero_image_desktop/public/features/img_2631.jpg?itok=FtidDft8")
val ElfinLakes = Trip("Elfin Lakes", "https://www.vancouvertrails.com/images/photos/elfin-lakes-3.jpg")

val FakeTrips = listOf(
    LindemanLake,
    EvansPeak,
    GoldCreek,
    ElfinLakes
)

val FakeWatchedUserTrips = listOf(
    UserTrip("5", ElfinLakes, emptyList()),
    UserTrip("6", EvansPeak, emptyList()),
    UserTrip("7", LindemanLake, emptyList())
)

val FakePlannedUserTrips = listOf(
    UserTrip("1", LindemanLake, emptyList()),
    UserTrip("2", EvansPeak, emptyList()),
    UserTrip("3", GoldCreek, emptyList())
)