package com.johnturkson.tripwatch.android.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onDispose
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.AmbientContext
import coil.Coil
import coil.ImageLoader
import coil.request.ImageRequest
import com.johnturkson.tripwatch.android.data.Result
import com.johnturkson.tripwatch.android.ui.state.UiState
import com.johnturkson.tripwatch.android.ui.state.copyWithResult

@Composable
fun loadPicture(url: String): UiState<Drawable> {
    val bitmapState =  mutableStateOf(UiState<Drawable>(loading = true))

    val request = ImageRequest.Builder(AmbientContext.current)
        .data(url)
        .target { drawable ->
            bitmapState.value = bitmapState.value.copyWithResult(Result.Success(drawable))
        }
        .build()

    val requestDisposable = Coil.imageLoader(AmbientContext.current).enqueue(request)

    onDispose {
        requestDisposable.dispose()
    }

    return bitmapState.value
}