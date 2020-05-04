package com.monh.packager.ui.home.my_orders.order_preparation

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.products.Product
import javax.inject.Inject

class OrderPreparationViewModel @Inject constructor() : BaseViewModel() {
    val orderProductsLiveData : MutableLiveData<List<Product>> = MutableLiveData()

    fun addList(products: Array<Product>) {
        orderProductsLiveData.postValue(products.toList())
    }

}
