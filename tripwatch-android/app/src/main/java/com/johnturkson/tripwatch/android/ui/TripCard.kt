package com.johnturkson.tripwatch.android.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.johnturkson.tripwatch.android.utils.loadPicture
import com.johnturkson.tripwatch.common.data.Trip

@Composable
fun TripImage(tripData : Trip) {
    val loadPictureState = loadPicture(url = tripData.imageUrl)

    if(loadPictureState.data != null) {
        Image(bitmap = loadPictureState.data.toBitmap().asImageBitmap())
    }
    else {
        Text("Loading....")
    }
}

@Composable
fun TripPeople() {
    Row {

    }
}

@Composable
fun TripCard() {

}

