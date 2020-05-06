package com.monh.packager.ui.home

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.auth.OrdersStatistics
import com.monh.packager.data.remote.auth.User
import com.monh.packager.data.remote.auth.UserRepository
import com.monh.packager.data.remote.products.Product
import com.monh.packager.utils.Event
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel(){
    val userLiveData:MutableLiveData<User> = MutableLiveData()
    val ordersStatisticsLiveData:MutableLiveData<OrdersStatistics> = MutableLiveData()
    val statusOnline:MutableLiveData<Boolean> = MutableLiveData()
    val foundedProduct:MutableLiveData<Event<Product>> = MutableLiveData()
    fun getUserInfo(){
        userRepository.getUser().let {
            userLiveData.postValue(it)
        }
    }

    fun getStatusOnline(){
        userRepository.getStatusOnline().let {
            statusOnline.postValue(it)
        }
    }

    fun getOrdersStatistics(){
        wrapBlockingOperation(showLoading = false) {
            handleResult(userRepository.getOrdersStatistics()){
                ordersStatisticsLiveData.postValue(it.data)
            }
        }
    }

    fun changeStatus(isChecked: Boolean) {
        wrapBlockingOperation {
            handleResult(userRepository.changeStatus(isChecked)){
                userRepository.saveNewPackagerStatus(isChecked)
            }
        }
    }

    fun markProductAsFound(product: Product) {
        foundedProduct.postValue(Event(product))
    }

}