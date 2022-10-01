package com.catnip.detailmovie.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.wrapper.ViewResource
import com.catnip.detailmovie.data.network.repository.DetailMovieRepsitory
import com.catnip.shared.data.model.mapper.MovieMapper
import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.catnip.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class GetDetailMovieUseCase(
    private val repository: DetailMovieRepsitory,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<GetDetailMovieUseCase.Param, MovieViewParam>(dispatcher) {

    override suspend fun execute(param: Param?): Flow<ViewResource<MovieViewParam>> = flow {
        param?.let {
            emit(ViewResource.Loading())
            repository.fetchDetailMovie(param.movieId).collect { response ->
                response.suspendSubscribe(
                    doOnSuccess = { result ->
                        emit(ViewResource.Success(MovieMapper.toViewParam(result.payload?.data)))
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        } ?: throw IllegalStateException("Param Required")
    }

    data class Param(val movieId: String)
}