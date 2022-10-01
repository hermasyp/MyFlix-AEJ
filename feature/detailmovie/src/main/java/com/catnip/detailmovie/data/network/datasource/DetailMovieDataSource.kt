package com.catnip.detailmovie.data.network.datasource

import com.catnip.detailmovie.data.network.services.DetailMovieFeatureApi
import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.shared.data.model.response.MovieResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface DetailMovieDataSource {
    suspend fun fetchDetailMovie(movieId: String): BaseResponse<MovieResponse>
}

class DetailMovieDataSourceImpl(private val detailMovieFeatureApi: DetailMovieFeatureApi) :
    DetailMovieDataSource {
    override suspend fun fetchDetailMovie(movieId: String): BaseResponse<MovieResponse> {
        return detailMovieFeatureApi.fetchDetailMovie(movieId)
    }
}