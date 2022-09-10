package com.catnip.core.exception

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class FieldErrorException(val errorFields: List<Pair<Int, Int>>) : Exception()