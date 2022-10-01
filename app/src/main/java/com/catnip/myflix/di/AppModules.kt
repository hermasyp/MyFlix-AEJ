package com.catnip.myflix.di

import com.catnip.core.base.BaseModules
import com.catnip.myflix.router.ActivityRouterImpl
import com.catnip.myflix.router.BottomSheetRouterImpl
import com.catnip.shared.router.ActivityRouter
import com.catnip.shared.router.BottomSheetRouter
import org.koin.core.module.Module
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object AppModules : BaseModules {
    override fun getModules(): List<Module> = listOf(routers)

    val routers: Module = module {
        single<ActivityRouter> { ActivityRouterImpl() }
        single<BottomSheetRouter> { BottomSheetRouterImpl() }
    }
}