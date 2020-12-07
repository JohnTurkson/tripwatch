package com.johnturkson.tripwatch.common.data

import kotlinx.serialization.Serializable

/**
* UserTrip is a trip that a user creates
* It is a planned trip that involves users, has start/end time, has emergency contacts, etc...
*
*  @param tripId - the id of the trip that can be used to get external data about the trip
*  @param tripData - the core "location" of the trip (location, image of the trip, name of the trail)
 * @param userIds - the people that are coming on the trip (who also have this trip as a "planned" trip)
*/

@Serializable
data class UserTrip(val tripId : String, val tripData : Trip, val userIds : List<String>)