package com.monh.packager.data.remote.auth

import com.ahmoneam.basecleanarchitecture.base.data.model.BaseResponse
import com.monh.packager.utils.network.Services.EndPoints.LOG_IN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST(LOG_IN)
    suspend fun logIn(@Body signInRequest: SignInRequest):Response<BaseResponse<User>>
}