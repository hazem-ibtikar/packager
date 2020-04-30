package com.monh.packager.data.remote.auth

import com.monh.packager.base.BaseRepository
import com.monh.packager.utils.InformativeResponse
import com.monh.packager.utils.network.ApplicationException
import com.monh.packager.utils.network.ErrorType
import com.monh.packager.utils.network.Result
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService
) : BaseRepository(){

    suspend fun signInUser(signInRequest: SignInRequest):Result<User>{
        return safeApiCall { userService.logIn(signInRequest) }
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

    suspend fun updateUserToken(firebaseToken: String):Result<InformativeResponse>{
        return safeApiCall { userService.sendFireBaseToken(UserTokenRequest(firebaseToken)) }
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

    suspend fun changeStatus(isChecked: Boolean):Result<InformativeResponse>{
        return safeApiCall { userService.updatePackagerStatus(UpdateStatusRequest(isChecked.toString())) }
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

    suspend fun getOrdersStatistics():Result<OrdersStatistics>{
        return safeApiCall { userService.getPackagerStatistics() }
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

    fun isUserLoggedIn():Boolean{
        return sharedPreferencesUtils.userLoginResponse != null
    }

    fun getUser():User?{
        return sharedPreferencesUtils.userLoginResponse
    }

    fun saveUser(user:User){
        sharedPreferencesUtils.userLoginResponse = user
    }

    fun getStatusOnline():Boolean{
        return sharedPreferencesUtils.statusOnline
    }

    fun saveNewPackagerStatus(isChecked: Boolean) {
        sharedPreferencesUtils.statusOnline = isChecked
    }

}