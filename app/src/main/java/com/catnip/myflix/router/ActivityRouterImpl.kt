package com.catnip.myflix.router

import android.content.Context
import android.content.Intent
import com.catnip.home.presentation.ui.HomeActivity
import com.catnip.login.presentation.ui.LoginActivity
import com.catnip.shared.router.ActivityRouter

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ActivityRouterImpl : ActivityRouter {
    override fun loginActivity(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

    override fun homeActivity(context: Context): Intent {
        return Intent(context, HomeActivity::class.java)
    }
}