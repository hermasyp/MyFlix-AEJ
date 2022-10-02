package com.catnip.profile.presentation.ui

import android.app.AlertDialog
import android.content.Intent
import android.widget.Toast
import androidx.core.view.isVisible
import com.catnip.core.base.BaseActivity
import com.catnip.core.exception.FieldErrorException
import com.catnip.profile.R
import com.catnip.profile.constants.UpdateProfileFieldConstant
import com.catnip.profile.databinding.ActivityProfileBinding
import com.catnip.shared.router.ActivityRouter
import com.catnip.shared.utils.ext.subscribe
import com.catnip.shared.utils.textdrawable.ColorGenerator
import com.catnip.shared.utils.textdrawable.TextDrawable
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity :
    BaseActivity<ActivityProfileBinding, ProfileViewModel>(ActivityProfileBinding::inflate) {
    override val viewModel: ProfileViewModel by viewModel()
    private val router: ActivityRouter by inject()

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        enableHomeAsBack()
    }


    override fun initView() {
        setupToolbar()
        initData()
        setClickListeners()
    }

    private fun initData() {
        viewModel.getCurrentUser()
    }

    private fun setClickListeners() {
        binding.btnLogout.setOnClickListener {
            openPromptLogout()
        }

        binding.btnUpdate.setOnClickListener {
            viewModel.updateProfileData(
                username = binding.etUsername.text?.trim().toString(),
            )
        }
    }

    override fun observeData() {
        viewModel.updateProfileResult.observe(this) { registerResult ->
            resetField()
            registerResult.subscribe(doOnLoading = {
                showLoading(true)
            }, doOnSuccess = {
                showLoading(false)
                Toast.makeText(
                    this,
                    getString(R.string.text_update_profile_success),
                    Toast.LENGTH_SHORT
                ).show()
            }, doOnError = {
                showLoading(false)
                if (registerResult.exception is FieldErrorException) {
                    handleFieldError(registerResult.exception as FieldErrorException)
                } else {
                    showError(true, registerResult.exception as Exception)
                }
            })
        }
        viewModel.logoutResult.observe(this) {
            it.subscribe(doOnSuccess = {
                navigateToLogin()
            }, doOnError = {
                Toast.makeText(this, getString(R.string.text_failed_logout), Toast.LENGTH_SHORT)
                    .show()
            })
        }
        viewModel.currentUserResult.observe(this) {
            it.subscribe(doOnSuccess = { result ->
                val user = result.payload
                binding.ivAvatarUser.setImageDrawable(
                    TextDrawable.builder()
                        .beginConfig()
                        .bold()
                        .toUpperCase()
                        .endConfig()
                        .buildRect(
                            user?.username?.get(0).toString(),
                            ColorGenerator.MATERIAL.randomColor
                        )
                )
                binding.etUsername.setText(user?.username.orEmpty())
            })
        }
    }

    private fun handleFieldError(exception: FieldErrorException) {
        exception.let {
            it.errorFields.forEach { errorField ->
                if (errorField.first == UpdateProfileFieldConstant.USERNAME_FIELD) {
                    binding.etUsername.error = getString(errorField.second)
                }
            }
        }
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.pbLoading.isVisible = isShowLoading
        binding.btnUpdate.isEnabled = !isShowLoading

    }

    private fun resetField() {
        binding.tilUsername.isErrorEnabled = false
    }

    private fun navigateToLogin() {
        startActivity(router.loginActivity(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }

    private fun openPromptLogout() {
        AlertDialog.Builder(this).apply {
            setMessage(R.string.message_logout)
                .setPositiveButton(R.string.logout) { dialog, id ->
                    viewModel.doLogout()
                }
                .setNegativeButton(R.string.cancel) { dialog, id ->
                    dialog.dismiss()
                }
        }.create().show()

    }
}