package com.johnturkson.tripwatch.android.ui

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.johnturkson.tripwatch.android.utils.getMutableStateOf
import com.johnturkson.tripwatch.common.data.User

/**
 * Screen names (used for serialization)
 */
enum class ScreenName { LAUNCH, HOME, PROFILE }

/**
 * Class defining the screens we have in the app: home, article details and interests
 */
sealed class Screen(val id: ScreenName) {
    object Home : Screen(ScreenName.HOME)
    object Profile : Screen(ScreenName.PROFILE)
    data class Launch(val email : String) : Screen(ScreenName.LAUNCH)
}

/**
 * Helpers for saving and loading a [Screen] object to a [Bundle].
 *
 * This allows us to persist navigation across process death, for example caused by a long video
 * call.
 */
private const val SIS_SCREEN = "sis_screen"
private const val SIS_NAME = "screen_name"
private const val SIS_EMAIL = "screen_email"

private fun Bundle.getStringOrThrow(key: String) =
    requireNotNull(getString(key)) { "Missing key '$key' in $this" }

/**
 * Convert a screen to a bundle that can be stored in [SavedStateHandle]
 */
private fun Screen.toBundle(): Bundle {
    return bundleOf(SIS_NAME to id.name).also {
        // add extra keys for various types here
        if (this is Screen.Launch) {
            it.putString(SIS_EMAIL, email)
        }
    }
}

/**
 * Read a bundle stored by [Screen.toBundle] and return a screen
 */

private fun Bundle.toScreen(): Screen {
    val screenName = ScreenName.valueOf(getStringOrThrow(SIS_NAME))
    return when (screenName) {
        ScreenName.LAUNCH -> {
            val email = getStringOrThrow(SIS_EMAIL)
            Screen.Launch(email)
        }
        ScreenName.HOME -> Screen.Home
        ScreenName.PROFILE -> Screen.Profile
    }
}

val DEFAULT_SCREEN = Screen.Home
//val DEFAULT_SCREEN = Screen.Launch("")

class NavigationViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    /**
     * Hold the current screen in an observable, restored from savedStateHandle after process
     * death.
     *
     * mutableStateOf is an observable similar to LiveData that's designed to be read by compose. It
     * supports observability via property delegate syntax as shown here.
     */
    var currentScreen: Screen by savedStateHandle.getMutableStateOf<Screen>(
        key = SIS_SCREEN,
        default = DEFAULT_SCREEN,
        save = { it.toBundle() },
        restore = { it.toScreen() }
    )
        private set // limit the writes to only inside this class.

    /**
     * Navigate to requested [Screen].
     *
     * If the requested screen is not [Home], it will always create a back stack with one element:
     * ([Home] -> [screen]). More back entries are not supported in this app.
     */
    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }
}