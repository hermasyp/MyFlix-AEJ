package com.catnip.home.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.wrapper.ViewResource
import com.catnip.home.data.network.repository.HomeRepository
import com.catnip.shared.data.model.mapper.MovieMapper
import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.catnip.shared.utils.ListMapper
import com.catnip.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class GetUserWatchlistUseCase(
    private val repository: HomeRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, List<MovieViewParam>>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<MovieViewParam>>> {
        return flow {
            emit(ViewResource.Loading())
            repository.fetchWatchlist().collect {
                it.suspendSubscribe(
                    doOnSuccess = { response ->
                        val movies = response.payload?.data
                        if (movies.isNullOrEmpty()) {
                            emit(ViewResource.Empty())
                        } else {
                            emit(ViewResource.Success(ListMapper(MovieMapper).toViewParams(movies)))
                        }
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
    }
}