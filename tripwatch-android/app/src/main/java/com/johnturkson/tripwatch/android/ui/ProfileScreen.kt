package com.johnturkson.tripwatch.android.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.johnturkson.tripwatch.R
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.android.ui.ProfileImage
import com.johnturkson.tripwatch.android.utils.*
import com.johnturkson.tripwatch.common.data.User

@Composable
fun ProfileScreen(appContainer : AppContainer, navigationViewModel : NavigationViewModel, userId : String) {

    val userData =
        if (userId == CURRENT_USER_ID_KEY)
            getUserDataFromId(appContainer.userData.id)
        else
            getUserDataFromId(userId)

    Scaffold(bottomBar = { BottomBar(appContainer, TripwatchTab.PROFILE, navigationViewModel::navigateTo) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {

            Image(bitmap = imageResource(R.drawable.trip_watch),
                modifier = Modifier.align(Alignment.Start).padding(horizontal = 16.dp).preferredWidth(128.dp).preferredHeight(48.dp))

            Spacer(modifier = Modifier.preferredHeight(32.dp))

            ProfileImage(userData = userData,
                modifier = Modifier.preferredWidth(128.dp)
                    .preferredHeight(128.dp))

            Text(text = userData.email)

            if(appContainer.userData.id == userData.id) {
                Button(
                    onClick = {
                        navigationViewModel.navigateTo(Screen.Launch(appContainer.userData.email))
                        appContainer.userData = User("", "")
                    })
                {

                    Text("Log Out")
                }
            }
        }
    }
}

@Composable
fun ProfileImage(userData : User, modifier : Modifier) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        getUserProfilePictureUrl(userData.id)?.let {
            URLImage(
                url = it,
                enterTransition = AnimationType.SLIDE_VERTICALLY,
                modifier = modifier.clip(CircleShape)
            )
        }
    }
}
