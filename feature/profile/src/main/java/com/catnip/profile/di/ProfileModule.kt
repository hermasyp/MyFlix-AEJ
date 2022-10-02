package com.catnip.profile.di

import com.catnip.core.base.FeatureModules
import com.catnip.profile.data.network.datasource.ProfileDataSource
import com.catnip.profile.data.network.datasource.ProfileDataSourceImpl
import com.catnip.profile.data.network.services.ProfileFeatureApi
import com.catnip.profile.data.repository.ProfileRepository
import com.catnip.profile.data.repository.ProfileRepositoryImpl
import com.catnip.profile.domain.CheckProfileUpdateFieldUseCase
import com.catnip.profile.domain.UpdateProfileUseCase
import com.catnip.profile.presentation.ui.ProfileViewModel
import com.catnip.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object ProfileModule : FeatureModules {
    override fun getModules(): List<Module> =
        listOf(repositories, viewModels, dataSources, useCases, network)

    override val repositories: Module = module {
        single<ProfileRepository> { ProfileRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::ProfileViewModel)
    }

    override val dataSources: Module = module {
        single<ProfileDataSource> { ProfileDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { CheckProfileUpdateFieldUseCase(Dispatchers.IO) }
        single { UpdateProfileUseCase(get(), get(), get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<ProfileFeatureApi> { get<NetworkClient>().create() }
    }
}