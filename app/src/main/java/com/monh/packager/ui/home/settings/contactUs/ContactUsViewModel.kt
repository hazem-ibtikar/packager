package com.monh.packager.ui.home.settings.contactUs

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.seller.ContactUsResponse
import com.monh.packager.data.remote.seller.SellerRepository
import javax.inject.Inject

class ContactUsViewModel @Inject constructor(
    private val sellerRepository: SellerRepository
) : BaseViewModel() {

    val contactUsLiveData:MutableLiveData<ContactUsResponse> = MutableLiveData()

    fun getContactUs(){
        wrapBlockingOperation {
            handleResult(sellerRepository.getContactUs()){
                contactUsLiveData.postValue(it.data)
            }
        }
    }

}
