package com.catnip.core.utils

import android.content.Context
import com.catnip.core.exception.ApiErrorException
import com.catnip.core.exception.NoInternetConnectionException

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

fun Context.getErrorMessage(exception : Exception): String {
    return when (exception) {
        is NoInternetConnectionException -> getString(com.catnip.styling.R.string.message_error_no_internet)
        is ApiErrorException -> exception.message.orEmpty()
        else -> getString(com.catnip.styling.R.string.message_error_unknown)
    }
}