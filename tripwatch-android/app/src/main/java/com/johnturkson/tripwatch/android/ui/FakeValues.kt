package com.johnturkson.tripwatch.android.ui

import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.User
import com.johnturkson.tripwatch.common.data.UserTrip

val FakeUserData = User("12345678", "solarcactus@live.com")
val FakeUserData1 = User("91234178", "kasr123@gmail.com")
val FakeUserData2 = User("1323123", "tttttt@gmail.com")

val FakeUsers = listOf(FakeUserData, FakeUserData1)

val FakeUserProfilePictures = mapOf(
    Pair("12345678", "https://www.thisbigwildworld.com/wp-content/uploads/2020/08/Best-Face-Mask-for-Hikers-Feature-Image.jpg"),
    Pair("91234178", "https://i.guim.co.uk/img/media/246a91071fe63541141a2abc095619d9c167f259/0_0_1080_1350/master/1080.jpg?width=700&quality=85&auto=format&fit=max&s=0dd6ebe093d7bfa42862d2d3e0ad7ec4"),
    Pair("1323123", "https://i.cbc.ca/1.3619577.1465263534!/fileImage/httpImage/image.jpg_gen/derivatives/16x9_780/andrew-chang-west-coast-trail.jpg"),
)

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