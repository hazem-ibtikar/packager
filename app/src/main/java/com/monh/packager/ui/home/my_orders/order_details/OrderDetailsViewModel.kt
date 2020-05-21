package com.monh.packager.ui.home.my_orders.order_details

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.orders.Order
import com.monh.packager.data.remote.orders.OrdersRepository
import com.monh.packager.data.remote.products.Product
import com.monh.packager.data.remote.products.ProductsRepository
import com.monh.packager.utils.Event
import javax.inject.Inject

class OrderDetailsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val ordersRepository: OrdersRepository
): BaseViewModel() {

    var selectedOrderId = 0
    val orderProductsLiveData : MutableLiveData<List<Product>> = MutableLiveData()
    val orderLiveData:MutableLiveData<Order> = MutableLiveData()
    val startOrderSuccessfully:MutableLiveData<Event<Boolean>> = MutableLiveData()

    fun getOrderProducts(orderId:Int){
        selectedOrderId = orderId
        wrapBlockingOperation {
            handleResult(productsRepository.getOrderProducts(orderId)){
                orderProductsLiveData.postValue(it.data.products)
                orderLiveData.postValue(it.data)
            }
        }
    }

    fun startNewOrder(){
        wrapBlockingOperation {
            handleResult(ordersRepository.startNewOrder(selectedOrderId)){
                startOrderSuccessfully.postValue(Event(true))
            }
        }
    }
}
