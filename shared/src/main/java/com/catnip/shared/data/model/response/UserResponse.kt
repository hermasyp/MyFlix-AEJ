package com.catnip.shared.data.model.response


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("birthdate")
    val birthdate: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("username")
    val username: String?
)