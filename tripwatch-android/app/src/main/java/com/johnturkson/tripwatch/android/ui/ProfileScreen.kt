package com.johnturkson.tripwatch.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.ui.ProfileImage
import com.johnturkson.tripwatch.android.utils.getUserProfilePictureUrl
import com.johnturkson.tripwatch.android.utils.loadPicture
import com.johnturkson.tripwatch.common.data.User

@Composable
fun ProfileScreen(appContainer : AppContainer, navigationViewModel : NavigationViewModel) {
    Scaffold(bottomBar = { BottomBar(appContainer, TripwatchTab.PROFILE, navigationViewModel::navigateTo) }) { innerPadding ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.preferredHeight(32.dp))
            ProfileImage(appContainer.profileDisplayUserData)
            Text(text = appContainer.profileDisplayUserData.email)
        }
    }
}

@Composable
fun ProfileImage(userData : User) {
    Box(modifier = Modifier.fillMaxWidth()) {
        val loadPictureState = loadPicture(getUserProfilePictureUrl(userData.id))
        if(loadPictureState.isLoaded) {
            Image(
                bitmap = loadPictureState.data!!.toBitmap().asImageBitmap(),
                modifier = Modifier.align(Alignment.Center)
                    .preferredWidth(128.dp).preferredHeight(128.dp).clip(CircleShape))
        }
    }
}
