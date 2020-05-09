package com.monh.packager.ui.home.my_orders.orders_list

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.orders.Order
import com.monh.packager.data.remote.orders.OrdersRepository
import com.monh.packager.utils.default
import javax.inject.Inject

class OrdersViewModel@Inject constructor(private val ordersRepository: OrdersRepository)  : BaseViewModel() {
    var ordersLiveData : MutableLiveData<MutableList<Order>> = MutableLiveData()
    var isLastPage:MutableLiveData<Boolean> = MutableLiveData<Boolean>().default(true)
    var isLoading = false
    var page = FIRST_PAGE

    fun getOrders(type:String, isReset:Boolean = false){
        wrapBlockingOperation(showLoading = page == FIRST_PAGE) {
            if (isReset){
                page = FIRST_PAGE
                ordersLiveData.value = arrayListOf()
            }
            handleResult(ordersRepository.getOrders(type, ++page)){
                if (ordersLiveData.value == null){
                    ordersLiveData.postValue(it.data.toMutableList())
                }else{
                    val oldOrders = ordersLiveData.value
                    oldOrders?.addAll(it.data)
                    ordersLiveData.postValue(oldOrders)
                }
                isLoading = false
                isLastPage.value = it.data.isEmpty()
            }
        }
    }
}
const val FIRST_PAGE = 0