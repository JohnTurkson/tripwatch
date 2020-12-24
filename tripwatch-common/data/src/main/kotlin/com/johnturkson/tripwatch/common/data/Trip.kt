package com.johnturkson.tripwatch.common.data

import kotlinx.serialization.Serializable

/**
 * Trip is data referring to the information about the location/look of a trip
 * @param tripName - name of the place that the trip is referring to
 * @param imageUrl - url of a display image of a trip
 */

@Serializable
data class Trip(val tripId : String, val tripName : String, val imageUrl : String)
