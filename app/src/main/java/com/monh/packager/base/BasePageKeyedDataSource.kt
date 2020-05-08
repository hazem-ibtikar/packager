package com.monh.packager.base

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.monh.packager.utils.network.ApplicationException
import com.monh.packager.utils.network.ErrorType
import com.monh.packager.utils.network.NetworkState
import com.monh.packager.utils.network.Result
import kotlinx.coroutines.runBlocking

class BasePageKeyedDataSource<T>(val apiCall: suspend (page:Int)->Result<List<T>>) : PageKeyedDataSource<Int, T>()  {

    private var retry: (() -> Any)? = null

    val networkState = MutableLiveData<NetworkState>()

    val initialNetworkState = MutableLiveData<NetworkState>()

    fun retry() {
        retry?.invoke()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        networkState.postValue(NetworkState.LOADING)
        initialNetworkState.postValue(NetworkState.LOADING)
        runBlocking{
            when(val result = apiCall(0)){
                is Result.Success -> {
                    retry = null
                    callback.onResult(result.data, null, 1)
                    initialNetworkState.postValue(NetworkState.LOADED)
                    networkState.postValue(NetworkState.LOADED)
                    Result.Success(result.data)
                }
                is Result.Error -> {
                    retry = {
                        loadInitial(params, callback)
                    }
                    val error = NetworkState.error(result.exception.errorMessageRes)
                    initialNetworkState.postValue(error)
                    networkState.postValue(error)
                    Result.Error(result.exception)
                }
                else -> {
                    Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        networkState.postValue(NetworkState.LOADING)
        runBlocking {
            when (val result = apiCall(params.key)) {
                is Result.Success -> {
                    retry = null
                    callback.onResult(result.data, params.key + 1)
                    networkState.postValue(NetworkState.LOADED)
                    Result.Success(result.data)
                }
                is Result.Error -> {
                    retry = {
                        loadAfter(params, callback)
                    }
                    networkState.postValue(NetworkState.error(result.exception.errorMessageRes))
                    Result.Error(result.exception)
                }
                else -> {
                    Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {}
}