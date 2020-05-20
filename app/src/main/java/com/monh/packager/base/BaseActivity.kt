package com.monh.packager.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.monh.packager.di.viewmodels.ViewModelFactory
import com.monh.packager.ui.auth.login.LoginActivity
import com.monh.packager.utils.EventObserver
import com.monh.packager.utils.LocaleHelper
import com.monh.packager.utils.MessageUtils
import com.monh.packager.utils.network.LoadingHandler
import com.monh.packager.utils.network.Result
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

open class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity() , HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var loadingHandler: LoadingHandler

    lateinit var viewModel:ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        LocaleHelper.onAttach(context = this)
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass())
        loadingHandler = LoadingHandler.getInstance(this)
        initLoading()
        initError()
    }

    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): Class<ViewModel> {
        // dirty hack to get generic type https://stackoverflow.com/a/1901275/719212
        return ((javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<ViewModel>)
    }
    private fun initError() {
        viewModel.error.observe(this, EventObserver {
            hideLoading()
            showError(it)
        })
    }
    open fun showError(error: Result.Error) {
        val errorMessage = error.exception.errorMessage
            ?: run {
                return@run if (error.exception.errorMessageRes != null) {
                    getString(error.exception.errorMessageRes)
                } else null
            }
            ?: "unexpected error"
        if (errorMessage.isNotEmpty())
            MessageUtils.showErrorMessage(this, errorMessage)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }
    private fun initLoading() {
        viewModel.loading.observe(this, EventObserver {
            if (it.loading) showLoading()
            else hideLoading()
        })
    }
    open fun hideLoading() {
        loadingHandler.hideLoading()
    }

    open fun showLoading() {
        loadingHandler.showLoading()
    }
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}