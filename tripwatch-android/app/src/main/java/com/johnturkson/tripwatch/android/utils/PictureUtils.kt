package com.johnturkson.tripwatch.android.utils

import android.graphics.drawable.Drawable
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onDispose
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import androidx.core.graphics.drawable.toBitmap
import coil.Coil
import coil.request.ImageRequest
import com.johnturkson.tripwatch.android.data.Result
import com.johnturkson.tripwatch.android.ui.state.UiState
import com.johnturkson.tripwatch.android.ui.state.copyWithResult


@OptIn(ExperimentalAnimationApi::class)
enum class AnimationType(
    val transition : EnterTransition) {

    FADE_IN(fadeIn()),
    SLIDE_HORIZONTALLY(slideInHorizontally(initialOffsetX = { 100 })
        + expandHorizontally(expandFrom = androidx.compose.ui.Alignment.End) + fadeIn(initialAlpha = 0.3f))
}

val pictureCache = mutableMapOf<String, Drawable>()

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun URLImage(url: String, enterTransition : AnimationType = AnimationType.FADE_IN, modifier : Modifier) {
    val bitmapState = remember { mutableStateOf(UiState<Drawable>(loading = true)) }

    if(!pictureCache.containsKey(url)) {
        val request = ImageRequest.Builder(AmbientContext.current)
            .data(url)
            .target { drawable ->
                bitmapState.value = bitmapState.value.copyWithResult(Result.Success(drawable))
                pictureCache[url] = drawable
            }
            .build()

        val requestDisposable = Coil.imageLoader(AmbientContext.current).enqueue(request)
        onDispose {
            requestDisposable.dispose()
        }
    }
    else {
        bitmapState.value = bitmapState.value.copyWithResult(Result.Success(pictureCache[url]!!))
    }

    AnimatedVisibility(
        visible = bitmapState.value.isLoaded,
        enter = enterTransition.transition) {
        if (bitmapState.value.isLoaded) {
            println(pictureCache)
            Image(
                bitmap = bitmapState.value.data!!.toBitmap().asImageBitmap(),
                modifier = modifier,
                contentScale = ContentScale.FillBounds
            )
        }
    }

}

