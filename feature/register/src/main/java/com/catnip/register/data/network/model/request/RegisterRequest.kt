package com.catnip.register.data.network.model.request


import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("birthdate")
    val birthdate: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("username")
    val username: String?
)