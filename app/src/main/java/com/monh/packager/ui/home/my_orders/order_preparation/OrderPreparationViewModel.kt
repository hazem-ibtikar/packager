package com.monh.packager.ui.home.my_orders.order_preparation

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.products.Product
import com.monh.packager.data.remote.products.ProductsRepository
import com.monh.packager.data.remote.products.UnFoundRequest
import javax.inject.Inject

class OrderPreparationViewModel @Inject constructor(private val productsRepository: ProductsRepository) : BaseViewModel() {
    val orderProductsLiveData : MutableLiveData<List<Product>> = MutableLiveData()

    fun addList(products: Array<Product>) {
        products.toList().apply {
            orderProductsLiveData.postValue(this.sortedBy { it.categoryId })
        }
    }

    fun markProductAsUnFound(productId:Int, orderId:String) {
        wrapBlockingOperation {
            handleResult(productsRepository.markOrderUnFound(UnFoundRequest(productID = productId.toString(), orderId = orderId))){
                handleUnFoundProduct(productId)
            }
        }
    }

    fun markProductAsFound(productId: Int){
        val oldProducts = orderProductsLiveData.value
        oldProducts?.find { it.id == productId.toString() }?.let {
            it.isFound = true
        }
        orderProductsLiveData.postValue(oldProducts)
    }

    private fun handleUnFoundProduct(productId: Int) {
        val oldProducts = orderProductsLiveData.value
        oldProducts?.find { it.id == productId.toString() }?.let {
            it.isNotFound = true
        }
        orderProductsLiveData.postValue(oldProducts)
    }

}
