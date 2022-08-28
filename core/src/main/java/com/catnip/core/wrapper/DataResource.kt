package com.catnip.core.wrapper

import java.lang.Exception

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

sealed class DataResource<T>(
    val payload: T? = null,
    val message: String? = null,
    val exception: Exception? = null,
) {
    class Success<T>(data: T) : DataResource<T>(data)
    class Error<T>(exception: Exception?, data: T? = null) : DataResource<T>(data, exception = exception)
}
