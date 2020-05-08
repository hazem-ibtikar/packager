package com.monh.packager.base

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.monh.packager.utils.network.Result

class BaseDataSourceFactory<T>(private val apiCall: suspend (page:Int)-> Result<List<T>>)  : DataSource.Factory<Int, T>() {

    val sourceLiveData = MutableLiveData<BasePageKeyedDataSource<T>>()
    override fun create(): DataSource<Int, T> {
        val source = BasePageKeyedDataSource(apiCall)
        sourceLiveData.postValue(source)
        return source
    }
}