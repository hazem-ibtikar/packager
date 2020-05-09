package com.monh.packager.data.remote.seller

import com.ahmoneam.basecleanarchitecture.base.data.model.BaseResponse
import com.monh.packager.utils.network.BaseListResponse
import com.monh.packager.utils.network.Services
import com.monh.packager.utils.network.Services.EndPoints.CONTACT_US
import com.monh.packager.utils.network.Services.EndPoints.TERMS_CONDITIONS
import retrofit2.Response
import retrofit2.http.GET

interface SellerService {

    @GET(CONTACT_US)
    suspend fun contactUS():Response<BaseResponse<ContactUsResponse>>

    @GET(TERMS_CONDITIONS)
    suspend fun getTermsAndConditions():Response<BaseResponse<TermsAndConditionsResponse>>

    @GET(Services.EndPoints.NOTIFICATIONS)
    suspend fun getNotifications(): Response<BaseResponse<NotificationWrapper>>
}