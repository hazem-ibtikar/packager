package com.monh.packager.ui.home.my_orders.order_details

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.products.Product
import com.monh.packager.data.remote.products.ProductsRepository
import javax.inject.Inject

class OrderDetailsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
): BaseViewModel() {

    val orderProductsLiveData : MutableLiveData<List<Product>> = MutableLiveData()

    fun getOrderProducts(orderId:Int){
        wrapBlockingOperation {
            handleResult(productsRepository.getOrderProducts(orderId)){
                orderProductsLiveData.postValue(it.data)
            }
        }
    }
}
