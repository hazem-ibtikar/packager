package com.monh.packager.ui.home.my_orders.order_details

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.orders.OrdersRepository
import com.monh.packager.data.remote.products.Product
import com.monh.packager.data.remote.products.ProductsRepository
import com.monh.packager.utils.Event
import javax.inject.Inject

class OrderDetailsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val ordersRepository: OrdersRepository
): BaseViewModel() {

    val orderProductsLiveData : MutableLiveData<List<Product>> = MutableLiveData()
    val startOrderSuccessfully:MutableLiveData<Event<Boolean>> = MutableLiveData()

    fun getOrderProducts(orderId:Int){
        wrapBlockingOperation {
            handleResult(productsRepository.getOrderProducts(orderId)){
                orderProductsLiveData.postValue(it.data)
            }
        }
    }

    fun startNewOrder(orderId:Int){
        wrapBlockingOperation {
            handleResult(ordersRepository.startNewOrder(orderId)){
                startOrderSuccessfully.postValue(Event(true))
            }
        }
    }
}
