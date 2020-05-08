package com.monh.packager.base

import android.os.Bundle
import androidx.lifecycle.Observer
import com.monh.packager.utils.network.Status

abstract class BasePagedFragment<ViewModel : BasePagedViewModel<T>, T>:BaseFragment<ViewModel>() {

    lateinit var adapter: BasePagedAdapter<T>
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = createPagedAdapter()
        getData()
        /*viewModel.networkState.observe(viewLifecycleOwner, Observer {
            adapter.setNetworkState(it)
        })*/
        /*viewModel.initialNetworkState.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.RUNNING -> {
                    *//*productsRV.visibility = View.GONE
                    loadingIndicator.visibility = View.VISIBLE
                    errorMessageTV.visibility = View.GONE*//*
                }
                Status.SUCCESS -> {
                    *//*productsRV.visibility = View.VISIBLE
                    loadingIndicator.visibility = View.GONE
                    errorMessageTV.visibility = View.GONE*//*
                }
                Status.FAILED -> {
                    *//*productsRV.visibility = View.GONE
                    loadingIndicator.visibility = View.GONE
                    errorMessageTV.visibility = View.VISIBLE
                    it.errorMessageRes?.let {
                        errorMessageTV.text = resources.getString(it)
                    }*//*
                }
            }
        })*/
    }

    abstract fun getData()

    abstract fun createPagedAdapter(): BasePagedAdapter<T>
}