package com.catnip.splashscreen.presentation

import android.widget.Toast
import com.catnip.core.base.BaseActivity
import com.catnip.shared.utils.ext.subscribe
import com.catnip.splashscreen.databinding.ActivitySplashScreenBinding
import org.koin.android.ext.android.inject

class SplashScreenActivity :
    BaseActivity<ActivitySplashScreenBinding, SplashScreenViewModel>(ActivitySplashScreenBinding::inflate) {

    override val viewModel: SplashScreenViewModel by inject()

    override fun initView() {
        viewModel.syncUser()
    }

    override fun observeData() {
        viewModel.syncResult.observe(this) {
            it.subscribe(
                doOnSuccess = { response ->
                    if (response.payload?.first == true) {
                        navigateToHome()
                    } else {
                        navigateToLogin()
                    }
                },
                doOnError = { error ->
                    Toast.makeText(this, error.exception?.message, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun navigateToLogin() {
        Toast.makeText(this, "Navigate to login", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToHome() {
        Toast.makeText(this, "Navigate to home", Toast.LENGTH_SHORT).show()
    }

}