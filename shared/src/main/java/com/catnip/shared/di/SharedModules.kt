package com.catnip.shared.di

import com.catnip.core.base.BaseModules
import com.catnip.shared.data.local.datastore.UserPreferenceDataSource
import com.catnip.shared.data.local.datastore.UserPreferenceDataSourceImpl
import com.catnip.shared.data.local.datastore.UserPreferenceFactory
import com.catnip.shared.data.remote.NetworkClient
import com.catnip.shared.data.remote.datasource.SharedFeatureApiDataSource
import com.catnip.shared.data.remote.datasource.SharedFeatureApiDataSourceImpl
import com.catnip.shared.data.remote.services.SharedFeatureApi
import com.catnip.shared.data.repository.SharedApiRepository
import com.catnip.shared.data.repository.SharedApiRepositoryImpl
import com.catnip.shared.data.repository.UserPreferenceRepository
import com.catnip.shared.data.repository.UserPreferenceRepositoryImpl
import com.catnip.shared.domain.GetCurrentUserUseCase
import com.catnip.shared.domain.GetUserTokenUseCase
import com.catnip.shared.domain.SaveAuthDataUseCase
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object SharedModules : BaseModules {
    override fun getModules(): List<Module> =
        listOf(remote, local, dataSource, repository, sharedUseCase, common)

    private val remote = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() }
        single { NetworkClient(get(), get()) }
        single<SharedFeatureApi> { get<NetworkClient>().create() }
    }

    private val local = module {
        single { UserPreferenceFactory(androidContext()).create() }
    }

    private val dataSource = module {
        single<UserPreferenceDataSource> { UserPreferenceDataSourceImpl(get(), get()) }
        single<SharedFeatureApiDataSource> { SharedFeatureApiDataSourceImpl(get()) }
    }

    private val repository = module {
        single<UserPreferenceRepository> { UserPreferenceRepositoryImpl(get()) }
        single<SharedApiRepository> { SharedApiRepositoryImpl(get()) }
    }
    private val sharedUseCase = module {
        single { GetUserTokenUseCase(get(), Dispatchers.IO) }
        single { SaveAuthDataUseCase(get(), Dispatchers.IO) }
        single { GetCurrentUserUseCase(get(), Dispatchers.IO) }
    }
    private val common = module {
        single { Gson() }
    }
}