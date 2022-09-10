package com.catnip.login.di

import com.catnip.core.base.FeatureModules
import com.catnip.login.data.network.datasource.LoginDataSource
import com.catnip.login.data.network.datasource.LoginDataSourceImpl
import com.catnip.login.data.network.service.LoginFeatureApi
import com.catnip.login.data.repository.LoginRepository
import com.catnip.login.data.repository.LoginRepositoryImpl
import com.catnip.login.domain.CheckLoginFieldUseCase
import com.catnip.login.domain.LoginUserUseCase
import com.catnip.login.presentation.ui.LoginViewModel
import com.catnip.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object LoginModules : FeatureModules {

    override fun getModules(): List<Module> = listOf(repositories, viewModels, dataSources, useCases, network)

    override val repositories: Module = module {
        single<LoginRepository> { LoginRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::LoginViewModel)
    }

    override val dataSources: Module = module {
        single<LoginDataSource> { LoginDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { CheckLoginFieldUseCase(Dispatchers.IO) }
        single { LoginUserUseCase(get(), get(), get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<LoginFeatureApi> { get<NetworkClient>().create() }
    }

}