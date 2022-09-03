package com.catnip.shared.data.model.response


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("cast")
    val cast: List<String>?,
    @SerializedName("category")
    val category: List<String>?,
    @SerializedName("director")
    val director: String?,
    @SerializedName("film_rate")
    val filmRate: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isUserWatchlist")
    val isUserWatchlist: Boolean?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_url")
    val posterUrl: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("trailer_url")
    val trailerUrl: String?,
    @SerializedName("video_url")
    val videoUrl: String?
)