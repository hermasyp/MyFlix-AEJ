package com.catnip.detailmovie.data.network.repository

import com.catnip.core.wrapper.DataResource
import com.catnip.detailmovie.data.network.datasource.DetailMovieDataSource
import com.catnip.shared.data.model.response.BaseResponse
import com.catnip.shared.data.model.response.MovieResponse
import com.catnip.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
typealias DetailDataResource = DataResource<BaseResponse<MovieResponse>>

interface DetailMovieRepsitory {
    suspend fun fetchDetailMovie(movieId: String): Flow<DetailDataResource>
}

class DetailMovieRepositoryImpl(private val detailMovieDataSource: DetailMovieDataSource) :
    Repository(),
    DetailMovieRepsitory {
    override suspend fun fetchDetailMovie(movieId: String): Flow<DetailDataResource> = flow {
        emit(safeNetworkCall { detailMovieDataSource.fetchDetailMovie(movieId) })
    }
}