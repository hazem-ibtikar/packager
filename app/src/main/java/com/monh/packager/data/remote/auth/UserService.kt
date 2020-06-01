package com.monh.packager.data.remote.auth

import com.monh.packager.utils.network.BaseResponse
import com.monh.packager.utils.InformativeResponse
import com.monh.packager.utils.network.Services.EndPoints.CHANGE_PASSWORD
import com.monh.packager.utils.network.Services.EndPoints.CHANGE_STATUS
import com.monh.packager.utils.network.Services.EndPoints.LOG_IN
import com.monh.packager.utils.network.Services.EndPoints.LOG_OUT
import com.monh.packager.utils.network.Services.EndPoints.RESET_PASSWORD
import com.monh.packager.utils.network.Services.EndPoints.UPDATE_PACKAGER
import com.monh.packager.utils.network.Services.EndPoints.UPLOAD_IMAGE
import com.monh.packager.utils.network.Services.EndPoints.USER_INFO
import com.monh.packager.utils.network.Services.EndPoints.USER_TOKEN
import com.monh.packager.utils.network.Services.Headers.UUID
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    @POST(LOG_IN)
    suspend fun logIn(@Body signInRequest: SignInRequest):Response<BaseResponse<LoginResponse>>

    @PUT(USER_TOKEN)
    suspend fun sendFireBaseToken(@Body userTokenRequest: UserTokenRequest):Response<BaseResponse<InformativeResponse>>

    @GET(USER_INFO)
    suspend fun getPackagerStatistics():Response<BaseResponse<OrdersStatistics>>

    @PUT(CHANGE_STATUS)
    suspend fun updatePackagerStatus(@Body updateStatusRequest: UpdateStatusRequest):Response<BaseResponse<InformativeResponse>>

    @PUT(RESET_PASSWORD)
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest):Response<BaseResponse<InformativeResponse>>

    @DELETE(LOG_OUT)
    suspend fun logOut(@Header(UUID) uuid:String):Response<BaseResponse<InformativeResponse>>

    @POST(CHANGE_PASSWORD)
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest):Response<BaseResponse<InformativeResponse>>

    @PUT(UPDATE_PACKAGER)
    suspend fun updatePackager(@Body packager: Packager):Response<BaseResponse<InformativeResponse>>

    @Multipart
    @POST(UPLOAD_IMAGE)
    suspend fun uploadImage(@Part image: MultipartBody.Part):Response<BaseResponse<UploadImageResponse>>
}