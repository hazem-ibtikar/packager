package com.monh.packager.data.remote.auth

import com.ahmoneam.basecleanarchitecture.base.data.model.BaseResponse
import com.monh.packager.utils.InformativeResponse
import com.monh.packager.utils.network.Services.EndPoints.CHANGE_STATUS
import com.monh.packager.utils.network.Services.EndPoints.LOG_IN
import com.monh.packager.utils.network.Services.EndPoints.LOG_OUT
import com.monh.packager.utils.network.Services.EndPoints.RESET_PASSWORD
import com.monh.packager.utils.network.Services.EndPoints.USER_INFO
import com.monh.packager.utils.network.Services.EndPoints.USER_TOKEN
import com.monh.packager.utils.network.Services.Headers.UUID
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    @POST(LOG_IN)
    suspend fun logIn(@Body signInRequest: SignInRequest):Response<BaseResponse<LoginResponse>>

    @POST(USER_TOKEN)
    suspend fun sendFireBaseToken(@Body userTokenRequest: UserTokenRequest):Response<BaseResponse<InformativeResponse>>

    @GET(USER_INFO)
    suspend fun getPackagerStatistics():Response<BaseResponse<OrdersStatistics>>

    @POST(CHANGE_STATUS)
    suspend fun updatePackagerStatus(@Body updateStatusRequest: UpdateStatusRequest):Response<BaseResponse<InformativeResponse>>

    @PUT(RESET_PASSWORD)
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest):Response<BaseResponse<InformativeResponse>>

    @DELETE(LOG_OUT)
    suspend fun logOut(@Header(UUID) uuid:String):Response<BaseResponse<InformativeResponse>>
}