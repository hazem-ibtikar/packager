package com.monh.packager.ui.home.settings.terms

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.seller.SellerRepository
import com.monh.packager.data.remote.seller.TermsAndConditionsResponse
import javax.inject.Inject

class TermsAndConditionsViewModel @Inject constructor(
    private val sellerRepository: SellerRepository
): BaseViewModel() {
    val termsLiveData:MutableLiveData<TermsAndConditionsResponse> = MutableLiveData()
    fun getTermsAndConditions() {
        wrapBlockingOperation {
            handleResult(sellerRepository.getTermsAndConditions()){
                termsLiveData.postValue(it.data)
            }
        }
    }

}
