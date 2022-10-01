package com.catnip.detailmovie.data.network.services

import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.shared.data.model.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface DetailMovieFeatureApi {
    @GET("/api/v1/movie/{movie_id}")
    suspend fun fetchDetailMovie(@Path("movie_id") movieId: String): BaseResponse<MovieResponse>
}