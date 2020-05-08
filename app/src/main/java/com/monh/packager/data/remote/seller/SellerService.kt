package com.monh.packager.data.remote.seller

import com.ahmoneam.basecleanarchitecture.base.data.model.BaseResponse
import com.monh.packager.utils.network.Services.EndPoints.CONTACT_US
import retrofit2.Response
import retrofit2.http.GET

interface SellerService {

    @GET(CONTACT_US)
    suspend fun contactUS():Response<BaseResponse<ContactUsResponse>>
}