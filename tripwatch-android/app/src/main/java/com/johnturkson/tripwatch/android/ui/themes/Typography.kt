package com.johnturkson.tripwatch.android.ui.themes

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val TripwatchTypography = Typography(
    h1= TextStyle(
        color = Color(0xff3d3d3d),
        fontWeight = FontWeight.Bold,
        fontSize=24.sp),
    h2= TextStyle(
            color = Color(0xff3d3d3d),
            fontSize=20.sp),
    subtitle2 = TextStyle(
            color = Color(0xff555555),
            fontSize=12.sp
    )
)