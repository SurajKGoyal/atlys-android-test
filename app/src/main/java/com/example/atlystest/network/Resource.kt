package com.example.atlystest.network

import android.content.Context
import androidx.annotation.StringRes

sealed class Resource<T>(
    open val data: T? = null, open val message: UiText? = null
) {
    data class Success<T>(override val data: T) : Resource<T>(data)
    data class Error<T>(override val message: UiText, override val data: T? = null) :
        Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}

sealed class UiText {
    data class ServerString(val value: String) : UiText()
    class LocalString(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UiText()

    fun get(context: Context): String {
        return when (this) {
            is ServerString -> value
            is LocalString -> context.getString(resId, *args)
        }
    }
}