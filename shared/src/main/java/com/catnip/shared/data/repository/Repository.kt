package com.catnip.shared.data.repository

import com.catnip.core.base.BaseRepository
import com.catnip.shared.data.model.response.BaseResponse
import com.google.gson.Gson
import com.google.gson.JsonParseException
import okhttp3.ResponseBody
import org.koin.java.KoinJavaComponent.inject

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
open class Repository : BaseRepository() {
    private val gson: Gson by inject(Gson::class.java)

    override fun <T> getErrorMessageFromApi(response: T): String {
        val responseBody = response as ResponseBody
        return try {
            val body = gson.fromJson(responseBody.string(), BaseResponse::class.java)
            body.message ?: "Error Api"
        } catch (e: JsonParseException) {
            "Error Api"
        }

    }
}