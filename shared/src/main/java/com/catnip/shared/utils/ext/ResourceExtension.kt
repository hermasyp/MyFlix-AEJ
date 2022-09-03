package com.catnip.shared.utils.ext

import com.catnip.core.wrapper.DataResource
import com.catnip.core.wrapper.ViewResource

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

fun <T> ViewResource<T>.subscribe(
    doOnSuccess: ((resource: ViewResource<T>) -> Unit)? = null,
    doOnError: ((resource: ViewResource<T>) -> Unit)? = null,
    doOnLoading: ((resource: ViewResource<T>) -> Unit)? = null,
    doOnEmpty: ((resource: ViewResource<T>) -> Unit)? = null,
) {
    when (this) {
        is ViewResource.Success -> doOnSuccess?.invoke(this)
        is ViewResource.Error -> doOnError?.invoke(this)
        is ViewResource.Loading -> doOnLoading?.invoke(this)
        is ViewResource.Empty -> doOnEmpty?.invoke(this)
    }
}

suspend fun <T> ViewResource<T>.suspendSubscribe(
    doOnSuccess: (suspend (resource: ViewResource<T>) -> Unit)? = null,
    doOnError: (suspend (resource: ViewResource<T>) -> Unit)? = null,
    doOnEmpty: (suspend (resource: ViewResource<T>) -> Unit)? = null,
    doOnLoading: (suspend (resource: ViewResource<T>) -> Unit)? = null,
) {
    when (this) {
        is ViewResource.Success -> doOnSuccess?.invoke(this)
        is ViewResource.Error -> doOnError?.invoke(this)
        is ViewResource.Loading -> doOnLoading?.invoke(this)
        is ViewResource.Empty -> doOnEmpty?.invoke(this)
    }
}

suspend fun <T> DataResource<T>.suspendSubscribe(
    doOnSuccess: suspend (resource: DataResource<T>) -> Unit,
    doOnError: suspend (resource: DataResource<T>) -> Unit,
) {
    when (this) {
        is DataResource.Success -> doOnSuccess.invoke(this)
        is DataResource.Error -> doOnError.invoke(this)
        else -> {}
    }
}
