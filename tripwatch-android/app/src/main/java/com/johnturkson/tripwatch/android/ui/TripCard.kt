package com.johnturkson.tripwatch.android.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.johnturkson.tripwatch.R
import com.johnturkson.tripwatch.android.ui.state.UiState
import com.johnturkson.tripwatch.android.utils.loadPicture
import com.johnturkson.tripwatch.common.data.Trip

@Composable
fun TripImage(tripData : Trip, onClick : () -> Unit) {
    val loadPictureState = loadPicture(url = tripData.imageUrl)

    if(loadPictureState.isLoaded) {
        Image(
            bitmap = loadPictureState.data!!.toBitmap().asImageBitmap(),
            modifier = Modifier.clip(MaterialTheme.shapes.large).clickable(onClick = onClick),
            contentScale = ContentScale.FillBounds
        )
    }
}


@Composable
fun TripPeople(tripData : Trip) {
    Row {
        for(i in 1..3) {
            Box(modifier=Modifier.height(32.dp).width(32.dp)) {
                Image(
                    bitmap = imageResource(R.drawable.trip_watch_logo),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Composable
fun TripCard(tripData : Trip, modifier : Modifier, onClick : () -> Unit) {
    Box(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            TripImage(tripData, onClick)

            Box(modifier = Modifier.align(Alignment.TopStart).padding(8.dp)) {
                Text(
                    text = tripData.tripName,
                    style = MaterialTheme.typography.subtitle1
                )
            }

            Box(modifier = Modifier.align(Alignment.BottomEnd).padding(4.dp)) {
                TripPeople(tripData)
            }
        }

    }
}

