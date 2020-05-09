package com.monh.packager.data.remote.seller

import com.monh.packager.base.BaseRepository
import com.monh.packager.utils.network.ApplicationException
import com.monh.packager.utils.network.ErrorType
import javax.inject.Inject
import com.monh.packager.utils.network.Result

class SellerRepository @Inject constructor(
    private val sellerService: SellerService
) : BaseRepository(){
    suspend fun getContactUs():Result<ContactUsResponse>{
        return safeApiCall { sellerService.contactUS() }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }

    suspend fun getTermsAndConditions():Result<TermsAndConditionsResponse>{
        return safeApiCall { sellerService.getTermsAndConditions() }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }

    suspend fun getNotifications():Result<List<Notification>>{
        return safeApiCall { sellerService.getNotifications() }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data?.list!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }
}