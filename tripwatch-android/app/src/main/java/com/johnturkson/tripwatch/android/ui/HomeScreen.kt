package com.johnturkson.tripwatch.android.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.johnturkson.tripwatch.android.data.AppContainer
import com.johnturkson.tripwatch.common.data.Trip

@Composable
fun HomeScreen(appContainer : AppContainer, navigationViewModel: NavigationViewModel) {

    ScrollableColumn(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(text = "Trips you're watching",
                 modifier = Modifier.padding(16.dp),
                 style=TextStyle(fontWeight = FontWeight.Bold,
                                 fontSize=24.sp)
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
                            256,
                            192,
                            {}
                        )
                    }
                }
            }

            Text(text = "Planned Trips",
                modifier = Modifier.padding(16.dp),
                style=TextStyle(fontWeight = FontWeight.Bold,
                    fontSize=24.sp)
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
                            256,
                            192,
                            {}
                        )
                    }
                }
            }
        }
    }
}