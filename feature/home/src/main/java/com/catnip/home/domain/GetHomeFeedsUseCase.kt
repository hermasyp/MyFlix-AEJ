package com.catnip.home.domain

import com.catnip.core.base.BaseUseCase
import com.catnip.core.wrapper.ViewResource
import com.catnip.home.data.network.repository.HomeRepository
import com.catnip.home.mapper.SectionMapper
import com.catnip.home.presentation.viewparam.homeitem.HomeUiItem
import com.catnip.shared.data.model.mapper.MovieMapper
import com.catnip.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class GetHomeFeedsUseCase(
    private val repository: HomeRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, List<HomeUiItem>>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<HomeUiItem>>> = flow {
        emit(ViewResource.Loading())
        repository.fetchHomeFeeds().collect {
            it.suspendSubscribe(
                doOnSuccess = { result ->
                    val data = mutableListOf<HomeUiItem>()
                    result.payload?.data?.let { homeData ->
                        homeData.header?.let { movie ->
                            data.add(HomeUiItem.HeaderSectionItem(MovieMapper.toViewParam(movie)))
                        }
                        homeData.sections?.forEach { section ->
                            data.add(HomeUiItem.MovieSectionItem(SectionMapper.toViewParam(section)))
                        }
                    }
                    emit(ViewResource.Success(data))
                }, doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}