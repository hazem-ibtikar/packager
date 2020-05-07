package com.monh.packager.data.remote.auth

import com.ahmoneam.basecleanarchitecture.base.data.model.BaseResponse
import com.monh.packager.utils.InformativeResponse
import com.monh.packager.utils.network.Services.EndPoints.CHANGE_STATUS
import com.monh.packager.utils.network.Services.EndPoints.LOG_IN
import com.monh.packager.utils.network.Services.EndPoints.USER_INFO
import com.monh.packager.utils.network.Services.EndPoints.USER_TOKEN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST(LOG_IN)
    suspend fun logIn(@Body signInRequest: SignInRequest):Response<BaseResponse<LoginResponse>>

    @POST(USER_TOKEN)
    suspend fun sendFireBaseToken(@Body userTokenRequest: UserTokenRequest):Response<BaseResponse<InformativeResponse>>

    @GET(USER_INFO)
    suspend fun getPackagerStatistics():Response<BaseResponse<OrdersStatistics>>

    @POST(CHANGE_STATUS)
    suspend fun updatePackagerStatus(@Body updateStatusRequest: UpdateStatusRequest):Response<BaseResponse<InformativeResponse>>
}