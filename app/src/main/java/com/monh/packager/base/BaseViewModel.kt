package com.monh.packager.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monh.packager.utils.Event
import com.monh.packager.utils.network.ApplicationException
import com.monh.packager.utils.network.ErrorType
import com.monh.packager.utils.network.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

open class BaseViewModel: ViewModel() {
    val error = MutableLiveData<Event<Result.Error>>()
    val loading =
        MutableLiveData<Event<Result.Loading>>().apply { value = Event(Result.Loading(false)) }

    val logOutLiveData : MutableLiveData<Event<Boolean>> = MutableLiveData()

    inline fun wrapBlockingOperation(
        showLoading: Boolean = true,
        crossinline function: suspend CoroutineScope.() -> Unit
    ): Job {
        loading.value = Event(Result.Loading(showLoading))
        return viewModelScope.launch {
            try {
                function()
            } catch (throwable: Throwable) {
                handelError(throwable)
                Timber.e(throwable)
            } finally {
                loading.value = Event(Result.Loading(false))
            }
        }
    }
    fun <T> handleResult(result: Result<T>, onSuccess: (Result.Success<T>) -> Unit) {
        when (result) {
            is Result.Success<T> -> {
                onSuccess(result)
            }
            is Result.Error -> {
                throw result.exception
            }
        }
    }

    fun handelError(throwable: Throwable) {
        if (throwable is ApplicationException) {
            error.postValue(Event(Result.Error(throwable)))
            when (throwable.type) {
                ErrorType.Network.Unauthorized -> {
                    logOutLiveData.postValue(Event(true))
                }
                ErrorType.Network.ResourceNotFound -> {}
                ErrorType.Network.Unexpected -> {}
                ErrorType.Network.NoInternetConnection -> {}
                else -> {}
            }
        }
    }
}