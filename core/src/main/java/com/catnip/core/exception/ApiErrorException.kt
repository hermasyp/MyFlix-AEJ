package com.catnip.core.exception

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ApiErrorException(
    override val message: String? = null,
    val httpCode: Int? = null
) : Exception()