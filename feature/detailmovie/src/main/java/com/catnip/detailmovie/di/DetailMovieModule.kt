package com.catnip.detailmovie.di

import com.catnip.core.base.FeatureModules
import com.catnip.detailmovie.data.network.datasource.DetailMovieDataSource
import com.catnip.detailmovie.data.network.datasource.DetailMovieDataSourceImpl
import com.catnip.detailmovie.data.network.repository.DetailMovieRepositoryImpl
import com.catnip.detailmovie.data.network.repository.DetailMovieRepsitory
import com.catnip.detailmovie.data.network.services.DetailMovieFeatureApi
import com.catnip.detailmovie.domain.GetDetailMovieUseCase
import com.catnip.detailmovie.presentation.ui.detailmovie.DetailMovieViewModel
import com.catnip.detailmovie.presentation.ui.movieinfo.MovieInfoViewModel
import com.catnip.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object DetailMovieModule : FeatureModules {

    override fun getModules(): List<Module> =
        listOf(repositories, viewModels, dataSources, useCases, network)

    override val repositories: Module = module {
        single<DetailMovieRepsitory> { DetailMovieRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::DetailMovieViewModel)
        viewModelOf(::MovieInfoViewModel)
    }

    override val dataSources: Module = module {
        single<DetailMovieDataSource> { DetailMovieDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { GetDetailMovieUseCase(get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<DetailMovieFeatureApi> { get<NetworkClient>().create() }
    }

}