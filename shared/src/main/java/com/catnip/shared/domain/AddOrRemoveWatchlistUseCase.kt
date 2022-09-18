package com.catnip.shared.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.wrapper.DataResource
import com.catnip.core.wrapper.ViewResource
import com.catnip.shared.data.model.viewparam.MovieViewParam
import com.catnip.shared.data.repository.SharedApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class AddOrRemoveWatchlistUseCase(
    val repository: SharedApiRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<AddOrRemoveWatchlistUseCase.Param, MovieViewParam>(dispatcher) {

    override suspend fun execute(param: Param?): Flow<ViewResource<MovieViewParam>> {
        param?.let {
            val movie = param.movie
            val movieId = movie.id
            val action = if(param.movie.isUserWatchlist)
                repository.removeWatchlist(movieId.toString())
            else
                repository.addWatchlist(movieId.toString())

            return action.map { result ->
                when(result){
                    is DataResource.Success -> ViewResource.Success(movie.apply {
                        isUserWatchlist = isUserWatchlist.not()
                    })
                    is DataResource.Error -> ViewResource.Error(result.exception)
                }
            }.onStart { emit(ViewResource.Loading()) }

        } ?: throw IllegalStateException("Param Required")
    }

    data class Param(val movie: MovieViewParam)

}