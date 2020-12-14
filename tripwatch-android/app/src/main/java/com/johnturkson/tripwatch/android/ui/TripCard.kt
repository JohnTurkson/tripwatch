package com.johnturkson.tripwatch.android.ui

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.utils.AnimationType
import com.johnturkson.tripwatch.android.utils.URLImage
import com.johnturkson.tripwatch.android.utils.getUserDataFromId
import com.johnturkson.tripwatch.android.utils.getUserProfilePictureUrl
import com.johnturkson.tripwatch.common.data.Trip
import com.johnturkson.tripwatch.common.data.UserTrip

@Composable
fun TripImage(tripData : Trip, onClick : () -> Unit) {
    URLImage(url = tripData.imageUrl,
            enterTransition = AnimationType.SLIDE_HORIZONTALLY,
            modifier = Modifier.clip(MaterialTheme.shapes.large).clickable(onClick = onClick))
}


@Composable
fun TripPeople(tripData : UserTrip, appContainer: AppContainer, navigateTo : (Screen) -> Unit) {
    Row {
        for(userId in tripData.userIds) {
            Box(modifier=Modifier.height(32.dp).width(32.dp).padding(4.dp)) {
                getUserProfilePictureUrl(userId)?.let {
                    URLImage(
                        url = it,
                        enterTransition = AnimationType.SLIDE_HORIZONTALLY,
                        modifier = Modifier.clip(CircleShape).clickable(onClick = {
                            appContainer.profileDisplayUserData = getUserDataFromId(userId)
                            navigateTo(Screen.Profile)
                        })
                    )
                }
            }
        }
    }
}

@Composable
fun UserTripCard(userTripData : UserTrip, modifier : Modifier, appContainer: AppContainer, navigateTo : (Screen) -> Unit) {
    Box(modifier = modifier.padding(16.dp)) {
        TripImage(userTripData.tripData, onClick = {navigateTo(Screen.Home)})

        Box(modifier = Modifier.align(Alignment.TopStart).padding(8.dp)) {
            Text(
                text = userTripData.tripData.tripName,
                style = TextStyle(
                    color = Color(0xffffffff),
                    fontWeight = FontWeight.Bold,
                    fontSize=24.sp)
            )
        }

        Box(modifier = Modifier.align(Alignment.BottomEnd).padding(4.dp)) {
            TripPeople(userTripData, appContainer, navigateTo)
        }
    }
}

@Composable
fun TripCard(tripData : Trip, modifier : Modifier, onClick : () -> Unit) {
    Box(modifier = modifier.padding(16.dp)) {
        TripImage(tripData, onClick)

        Box(modifier = Modifier.align(Alignment.TopStart).padding(8.dp)) {
            Text(
                text = tripData.tripName,
                style = TextStyle(
                    color = Color(0xffffffff),
                    fontWeight = FontWeight.Bold,
                    fontSize=24.sp)
            )
        }
    }
}

