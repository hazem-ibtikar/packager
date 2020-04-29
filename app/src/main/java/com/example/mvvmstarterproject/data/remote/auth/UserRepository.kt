package com.example.mvvmstarterproject.data.remote.auth

import com.example.mvvmstarterproject.base.BaseRepository
import com.example.mvvmstarterproject.utils.ConnectivityUtils
import com.example.mvvmstarterproject.utils.network.ApplicationException
import com.example.mvvmstarterproject.utils.network.ErrorType
import com.example.mvvmstarterproject.utils.network.Result
import javax.inject.Inject

class UserRepository @Inject constructor(
    connectivityUtils: ConnectivityUtils,
    private val userService: UserService
) : BaseRepository(connectivityUtils){

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

    fun saveUser(user:User){

    }
}