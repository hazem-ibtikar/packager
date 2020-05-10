package com.monh.packager.ui.home.my_orders.order_preparation

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.orders.MarkAsPackaged
import com.monh.packager.data.remote.orders.OrderPackagedResponse
import com.monh.packager.data.remote.orders.OrdersRepository
import com.monh.packager.data.remote.products.Product
import com.monh.packager.data.remote.products.ProductsRepository
import com.monh.packager.data.remote.products.UnFoundRequest
import javax.inject.Inject

class OrderPreparationViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val ordersRepository: OrdersRepository
) : BaseViewModel() {
    val orderProductsLiveData : MutableLiveData<List<Product>> = MutableLiveData()

    val orderPackagedLiveData:MutableLiveData<OrderPackagedResponse> = MutableLiveData()
    fun addList(products: Array<Product>) {
        products.toList().apply {
            orderProductsLiveData.postValue(this.sortedBy { it.categoryId })
        }
    }

    fun markProductAsUnFound(productId:Int, orderId:String) {
        wrapBlockingOperation {
            handleResult(productsRepository.markOrderUnFound(productId)){
                handleUnFoundProduct(productId)
            }
        }
    }

    fun markProductAsFound(productId: Int){
        val oldProducts = orderProductsLiveData.value
        oldProducts?.find { it.id == productId }?.let {
            it.isFound = 1
        }
        orderProductsLiveData.postValue(oldProducts)
    }

    private fun handleUnFoundProduct(productId: Int) {
        val oldProducts = orderProductsLiveData.value
        oldProducts?.find { it.order_item_id == productId }?.let {
            it.setAsNotFound()
        }
        orderProductsLiveData.postValue(oldProducts)
    }

    fun markOrderAsPackaged(orderId:Int, cartons:Int){
        wrapBlockingOperation {
            handleResult(ordersRepository.markAsPackaged(MarkAsPackaged(orderId = orderId, cartons = cartons))){
                orderPackagedLiveData.postValue(it.data)
            }
        }
    }
}
