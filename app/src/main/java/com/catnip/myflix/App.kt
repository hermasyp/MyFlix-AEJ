package com.catnip.myflix

import android.app.Application
import com.catnip.detailmovie.di.DetailMovieModule
import com.catnip.home.di.HomeModules
import com.catnip.login.di.LoginModules
import com.catnip.myflix.di.AppModules
import com.catnip.profile.di.ProfileModule
import com.catnip.register.di.RegisterModules
import com.catnip.shared.di.SharedModules
import com.catnip.splashscreen.di.SplashScreenModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                AppModules.getModules() +
                        SharedModules.getModules() +
                        SplashScreenModules.getModules() +
                        LoginModules.getModules() +
                        RegisterModules.getModules() +
                        HomeModules.getModules() +
                        DetailMovieModule.getModules() +
                        ProfileModule.getModules()
            )
        }

    }
}