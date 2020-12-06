package com.johnturkson.tripwatch.android.ui.state

import com.johnturkson.tripwatch.android.data.Result as Result

data class UiState<T>(val loading : Boolean = false,
                      val exception : Exception? = null,
                      val data : T? = null) {

    val hasError : Boolean
        get() = exception != null

    val isInitialLoad : Boolean
        get() = data == null && loading && !hasError

    val isLoaded : Boolean
        get() = data != null && !loading && !hasError
}

fun <T> UiState<T>.copyWithResult(value: Result<T>): UiState<T> {
    return when (value) {
        is Result.Success -> copy(loading = false, exception = null, data = value.data)
        is Result.Error -> copy(loading = false, exception = value.exception)
    }
}