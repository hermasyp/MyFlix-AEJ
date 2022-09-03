package com.catnip.splashscreen.di

import com.catnip.core.base.FeatureModules
import com.catnip.shared.data.remote.NetworkClient
import com.catnip.splashscreen.data.network.datasource.SplashScreenDataSource
import com.catnip.splashscreen.data.network.datasource.SplashScreenDataSourceImpl
import com.catnip.splashscreen.data.network.service.SplashScreenFeatureApi
import com.catnip.splashscreen.data.repository.SplashScreenRepository
import com.catnip.splashscreen.data.repository.SplashScreenRepositoryImpl
import com.catnip.splashscreen.domain.SyncUserUseCase
import com.catnip.splashscreen.presentation.SplashScreenViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object SplashScreenModules : FeatureModules {
    override fun getModules(): List<Module> = listOf(
        repositories, viewModels, dataSources, useCases, network
    )

    override val repositories: Module = module {
        single<SplashScreenRepository> { SplashScreenRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::SplashScreenViewModel)
    }

    override val dataSources: Module = module {
        single<SplashScreenDataSource> { SplashScreenDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { SyncUserUseCase(get(), get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<SplashScreenFeatureApi> { get<NetworkClient>().create() }
    }
}