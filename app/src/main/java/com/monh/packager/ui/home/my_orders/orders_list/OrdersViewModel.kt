package com.monh.packager.ui.home.my_orders.orders_list

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.orders.Order
import com.monh.packager.data.remote.orders.OrdersRepository
import javax.inject.Inject

class OrdersViewModel@Inject constructor(private val ordersRepository: OrdersRepository)  : BaseViewModel() {
    val ordersLiveData : MutableLiveData<List<Order>> = MutableLiveData()
    fun getUrgentOrders(){
        wrapBlockingOperation {
            handleResult(ordersRepository.getUrgentOrders()){
                ordersLiveData.postValue(it.data)
            }
        }
    }

    fun getOpenOrders(){
        wrapBlockingOperation {
            handleResult(ordersRepository.getOpenOrders()){
                ordersLiveData.postValue(it.data)
            }
        }
    }

    fun getClosedOrders(){
        wrapBlockingOperation {
            handleResult(ordersRepository.getClosedOrders()){
                ordersLiveData.postValue(it.data)
            }
        }
    }
}
