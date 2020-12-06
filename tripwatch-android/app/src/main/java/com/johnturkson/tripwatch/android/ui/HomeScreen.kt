package com.johnturkson.tripwatch.android.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.johnturkson.tripwatch.R
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.common.data.Trip

@Composable
fun HomeScreen(appContainer : AppContainer, navigationViewModel: NavigationViewModel) {

    Scaffold(backgroundColor = MaterialTheme.colors.primarySurface,
            bottomBar = { BottomBar(navigationViewModel::navigateTo) }) { innerPadding ->

        ScrollableColumn(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(innerPadding)) {

                Spacer(modifier = Modifier.preferredHeight(32.dp))
                Text(
                    text = "Explore Trips",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h1
                )

                val tripCardModifier =
                    Modifier.fillMaxWidth().preferredWidth(256.dp).preferredHeight(192.dp)

                TripCard(
                    Trip(
                        "Lindeman Lake Trail",
                        emptyList(),
                        "https://upload.wikimedia.org/wikipedia/commons/3/32/Lindeman_Lake.jpg"
                    ),
                    tripCardModifier.align(Alignment.CenterHorizontally),
                    {}
                )


                Text(
                    text = "Trips you're watching",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.h1
                )

                ScrollableRow {
                    Row {
                        for (i in 0..2) {
                            TripCard(
                                Trip(
                                    "St. Marks Summit",
                                    emptyList(),
                                    "https://i.redd.it/van9afvxq0j31.jpg"
                                ),
                                tripCardModifier,
                                {}
                            )
                        }
                    }
                }

                Text(
                    text = "Trips you've planned",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.h1
                )

                ScrollableRow {
                    Row {
                        for (i in 0..2) {
                            TripCard(
                                Trip(
                                    "Evan's Peak",
                                    emptyList(),
                                    "https://www.ashikaparsad.com/wp-content/uploads/2015/05/View-of-Blanshard-and-Edge-Peaks-from-Evans-Peak-1024x683.jpg"
                                ),
                                tripCardModifier,
                                {}
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(navigateTo : (Screen) -> Unit) {
    val tabs = HomeTabs.values()
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(HomeTabs.HOME) }

    BottomNavigation(modifier = Modifier.padding(16.dp)) {
        tabs.forEach { tab ->
            BottomNavigationItem(
                icon = { Icon(vectorResource(tab.icon)) },
                selected = tab == selectedTab,
                onClick = { setSelectedTab(tab) },
                alwaysShowLabels = false,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = AmbientContentColor.current)
        }
    }
}

enum class HomeTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    HOME(R.string.home, R.drawable.person_icon),
    TRIP_WATCHER(R.string.trip_watcher, R.drawable.mountains_icon),
    PROFILE(R.string.profile, R.drawable.person_icon)
}