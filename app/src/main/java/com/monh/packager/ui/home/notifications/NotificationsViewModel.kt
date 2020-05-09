package com.monh.packager.ui.home.notifications


import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.seller.Notification
import com.monh.packager.data.remote.seller.SellerRepository
import com.monh.packager.utils.default
import javax.inject.Inject

class NotificationsViewModel @Inject constructor(
    private val sellerRepository: SellerRepository
) : BaseViewModel() {

    val notificationsLiveData:MutableLiveData<MutableList<Notification>> = MutableLiveData()
    var isLastPage: MutableLiveData<Boolean> = MutableLiveData<Boolean>().default(true)
    var isLoading = false
    var page = -1
    fun getNotifications(isReset:Boolean = false){
        wrapBlockingOperation (showLoading = page == -1){
            if (isReset){
                page = -1
                notificationsLiveData.value = arrayListOf()
            }
            handleResult(sellerRepository.getNotifications()){
                page++
                if (notificationsLiveData.value == null){
                    notificationsLiveData.postValue(it.data.toMutableList())
                }else{
                    val oldNotifications = notificationsLiveData.value
                    oldNotifications?.addAll(it.data)
                    notificationsLiveData.postValue(oldNotifications)
                }
                isLoading = false
                isLastPage.value = it.data.isEmpty()
            }
        }
    }
}
