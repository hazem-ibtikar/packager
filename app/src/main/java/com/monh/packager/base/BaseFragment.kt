package com.monh.packager.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.monh.packager.di.viewmodels.ViewModelFactory
import com.monh.packager.ui.auth.login.LoginActivity
import com.monh.packager.utils.EventObserver
import com.monh.packager.utils.MessageUtils
import com.monh.packager.utils.network.LoadingHandler
import com.monh.packager.utils.network.Result
import dagger.android.support.AndroidSupportInjection
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

open class BaseFragment<ViewModel : BaseViewModel> : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel:ViewModel
    private lateinit var loadingHandler: LoadingHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(getLifeCycleOwner(), viewModelFactory).get(viewModelClass())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadingHandler = LoadingHandler.getInstance(requireActivity())
        initLoading()
        initError()
        handleLogOut()
    }

    // called when un authorized
    private fun handleLogOut() {
        viewModel.logOutLiveData.observe(viewLifecycleOwner, EventObserver{
            logout()
        })
    }

    open fun logout() {
        activity?.startActivity(Intent(requireActivity(), LoginActivity::class.java))
    }

    open fun getLifeCycleOwner(): ViewModelStoreOwner {
        return this
    }
    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): Class<ViewModel> {
        // dirty hack to get generic type https://stackoverflow.com/a/1901275/719212
        return ((javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<ViewModel>)
    }
    private fun initError() {
        viewModel.error.observe(viewLifecycleOwner, EventObserver {
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
            MessageUtils.showErrorMessage(requireActivity(), errorMessage)
    }
    fun showErrorMsg(msgResource:Int){
        MessageUtils.showErrorMessage(requireActivity(), context?.getString(msgResource)?:"")
    }

    private fun initLoading() {
        viewModel.loading.observe(viewLifecycleOwner, EventObserver {
            if (it.loading) showLoading()
            else hideLoading()
        })
    }
    open fun hideLoading() {
        loadingHandler.hideLoading()
    }

    open fun showLoading() {
        hideKeyboard()
        loadingHandler.showLoading()
    }
    fun hideKeyboard() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}