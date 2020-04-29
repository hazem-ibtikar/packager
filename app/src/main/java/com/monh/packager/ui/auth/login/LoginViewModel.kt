package com.monh.packager.ui.auth.login


import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.auth.SignInRequest
import com.monh.packager.data.remote.auth.UserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userRepository: UserRepository): BaseViewModel() {
    val loginSuccessfulLiveData:MutableLiveData<Boolean> = MutableLiveData()

    fun signIn(email:String, password:String){
        wrapBlockingOperation {
            handleResult(userRepository.signInUser(SignInRequest(email = email, password = password))){
                // save user
                userRepository.saveUser(it.data)
                loginSuccessfulLiveData.postValue(true)
            }
        }
    }

    fun checkIfUserLoggedIn() : Boolean{
        return userRepository.isUserLoggedIn()
    }
}
