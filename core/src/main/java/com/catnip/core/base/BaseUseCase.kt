package com.catnip.core.base

import com.catnip.core.wrapper.ViewResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
abstract class BaseUseCase<P, R : Any?> constructor(private val dispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(param: P? = null): Flow<ViewResource<R>> {
        return execute(param)
            .catch { emit(ViewResource.Error(Exception(it))) }
            .flowOn(dispatcher)
    }

    @Throws(RuntimeException::class)
    abstract suspend fun execute(param: P? = null): Flow<ViewResource<R>>

}