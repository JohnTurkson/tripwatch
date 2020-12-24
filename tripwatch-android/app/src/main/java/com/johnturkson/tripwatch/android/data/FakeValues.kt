package com.johnturkson.tripwatch.android.ui

import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.User
import com.johnturkson.tripwatch.common.data.UserTrip

val FakeUserData = User("12345678", "solarcactus@live.com")
val FakeUserData1 = User("91234178", "kasr123@gmail.com")
val FakeUserData2 = User("1323123", "tttttt@gmail.com")

val FakeUsers = listOf(FakeUserData, FakeUserData1, FakeUserData2)

val FakeUserProfilePictures = mapOf(
    Pair("12345678", "https://s3.amazonaws.com/appforest_uf/f1512936020165x278911292087286720/A.png"),
    Pair("91234178", "https://cdn.thervo.com/assets/images/letters/H.png"),
    Pair("1323123", "https://alexdunndev.files.wordpress.com/2018/05/initialscircle.jpg?w=660"),
)

val FakeTripLocations = mapOf(
    Pair("lindeman_lake", "Chilliwack Lake Provincial Park, British Columbia"),
    Pair("evans_peak", "Golden Ears Provincial Park, British Columbia"),
    Pair("gold_creek", "Golden Ears Provincial Park, British Columbia"),
    Pair("elfin_lakes", "Garibaldi Provincial Park, British Columbia"),
)

val LindemanLake = Trip("lindeman_lake", "Lindeman Lake", "https://www.vancouvertrails.com/images/photos/lindeman-lake-5.jpg")
val EvansPeak = Trip( "evans_peak","Evans Peak", "https://jelgerandtanja.com/wp-content/uploads/2018/10/Evans-Peak-hike.jpg")
val GoldCreek = Trip( "gold_creek"  ,"Gold Creek", "https://www.outdoorproject.com/sites/default/files/styles/hero_image_desktop/public/features/img_2631.jpg?itok=FtidDft8")
val ElfinLakes = Trip("elfin_lakes","Elfin Lakes", "https://www.vancouvertrails.com/images/photos/elfin-lakes-3.jpg")

val FakeTrips = mapOf(
    Pair("lindeman_lake", LindemanLake),
    Pair("evans_peak", EvansPeak),
    Pair("gold_creek", GoldCreek),
    Pair("elfin_lakes", ElfinLakes)
)

val FakeWatchedUserTrips = listOf(
    UserTrip("5", ElfinLakes, listOf(FakeUserData1.id, FakeUserData2.id)),
    UserTrip("6", EvansPeak, listOf(FakeUserData1.id, FakeUserData2.id)),
    UserTrip("7", GoldCreek, listOf(FakeUserData1.id))
)

val FakePlannedUserTrips = listOf(
    UserTrip("1", LindemanLake, listOf(FakeUserData.id, FakeUserData2.id)),
    UserTrip("2", EvansPeak, listOf(FakeUserData.id)),
    UserTrip("3", GoldCreek, listOf(FakeUserData.id, FakeUserData1.id))
)