package com.catnip.register.di

import com.catnip.core.base.FeatureModules
import com.catnip.register.data.network.datasource.RegisterDataSource
import com.catnip.register.data.network.datasource.RegisterDataSourceImpl
import com.catnip.register.data.network.services.RegisterFeatureApi
import com.catnip.register.data.repository.RegisterRepository
import com.catnip.register.data.repository.RegisterRepositoryImpl
import com.catnip.register.domain.CheckRegisterFieldUseCase
import com.catnip.register.domain.RegisterUserUseCase
import com.catnip.register.presentation.ui.RegisterViewModel
import com.catnip.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object RegisterModules : FeatureModules {
    override fun getModules(): List<Module> =
        listOf(repositories, viewModels, dataSources, useCases, network)

    override val repositories: Module = module {
        single<RegisterRepository> { RegisterRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::RegisterViewModel)
    }

    override val dataSources: Module = module {
        single<RegisterDataSource> { RegisterDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { CheckRegisterFieldUseCase(Dispatchers.IO) }
        single { RegisterUserUseCase(get(), get(), get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<RegisterFeatureApi> { get<NetworkClient>().create() }
    }
}