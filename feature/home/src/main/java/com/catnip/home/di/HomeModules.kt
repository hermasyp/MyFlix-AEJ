package com.catnip.home.di

import com.catnip.core.base.FeatureModules
import com.catnip.home.data.network.datasource.HomeDataSource
import com.catnip.home.data.network.datasource.HomeDataSourceImpl
import com.catnip.home.data.network.repository.HomeRepository
import com.catnip.home.data.network.repository.HomeRepositoryImpl
import com.catnip.home.data.network.services.HomeFeatureApi
import com.catnip.home.domain.GetHomeFeedsUseCase
import com.catnip.home.domain.GetUserWatchlistUseCase
import com.catnip.home.presentation.ui.home.HomeViewModel
import com.catnip.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object HomeModules : FeatureModules {

    override fun getModules(): List<Module> =
        listOf(repositories, viewModels, dataSources, useCases, network)

    override val repositories: Module = module {
        single<HomeRepository> { HomeRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::HomeViewModel)
    }

    override val dataSources: Module = module {
        single<HomeDataSource> { HomeDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { GetHomeFeedsUseCase(get(), Dispatchers.IO) }
        single { GetUserWatchlistUseCase(get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<HomeFeatureApi> { get<NetworkClient>().create() }
    }

}