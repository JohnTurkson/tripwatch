package com.johnturkson.tripwatch.android.ui

import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.User
import com.johnturkson.tripwatch.common.data.UserTrip

val FakeUserData = User("12345678", "solarcactus@live.com")
val FakeUserData1 = User("91234178", "kasr123@gmail.com")

val FakeUsers = listOf(FakeUserData, FakeUserData1)

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
    UserTrip("5", ElfinLakes, listOf(FakeUserData1.id)),
    UserTrip("6", EvansPeak, listOf(FakeUserData1.id)),
    UserTrip("7", LindemanLake, listOf(FakeUserData1.id))
)

val FakePlannedUserTrips = listOf(
    UserTrip("1", LindemanLake, listOf(FakeUserData.id)),
    UserTrip("2", EvansPeak, listOf(FakeUserData.id)),
    UserTrip("3", GoldCreek, listOf(FakeUserData.id, FakeUserData1.id))
)