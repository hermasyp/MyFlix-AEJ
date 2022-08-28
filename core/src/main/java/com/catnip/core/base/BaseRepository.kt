package com.catnip.core.base

import com.catnip.core.exception.ApiErrorException
import com.catnip.core.exception.NoInternetConnectionException
import com.catnip.core.exception.UnexpectedErrorException
import com.catnip.core.wrapper.DataResource
import retrofit2.HttpException
import java.io.IOException

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
abstract class BaseRepository {

    abstract fun <T> getErrorMessageFromApi(response : T) : String

    suspend fun <T> safeNetworkCall(apiCall: suspend () -> T): DataResource<T> {
        return try {
            DataResource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> DataResource.Error(NoInternetConnectionException())
                is HttpException -> {
                    DataResource.Error(ApiErrorException(getErrorMessageFromApi(throwable.response()?.errorBody()), throwable.code()))
                }
                else -> {
                    DataResource.Error(UnexpectedErrorException())
                }
            }
        }
    }

    suspend fun <T> proceed(coroutine: suspend () -> T): DataResource<T> {
        return try {
            DataResource.Success(coroutine.invoke())
        } catch (exception: Exception) {
            DataResource.Error(exception)
        }
    }
}