package com.monh.packager.data.remote.auth

import com.monh.packager.base.BaseRepository
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

    fun isUserLoggedIn():Boolean{
        return sharedPreferencesUtils.userLoginResponse != null
    }
    fun saveUser(user:User){
        sharedPreferencesUtils.userLoginResponse = user
    }
}