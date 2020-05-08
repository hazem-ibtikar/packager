package com.monh.packager.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.monh.packager.utils.network.NetworkState
import com.monh.packager.utils.network.Result


open class BasePagedViewModel<T>:BaseViewModel() {
    lateinit var dataSourceFactory: BaseDataSourceFactory<T>
    lateinit var itemsList: LiveData<PagedList<T>>
    lateinit var initialNetworkState: LiveData<NetworkState>
    lateinit var networkState: LiveData<NetworkState>


    fun getList(apiCall: suspend (page: Int) -> Result<List<T>>) {
        val config = PagedList
            .Config
            .Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
        dataSourceFactory = BaseDataSourceFactory(apiCall)
        itemsList = LivePagedListBuilder(dataSourceFactory, config).build()
        initialNetworkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) { it.initialNetworkState }
        /*networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
            it.networkState
        }*/
    }

    fun retry() {
        dataSourceFactory.sourceLiveData.value?.retry()
    }

    fun refresh() {
        dataSourceFactory.sourceLiveData.value?.invalidate()
    }
}