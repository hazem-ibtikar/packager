package com.monh.packager.ui.auth.forgot

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.auth.ResetPasswordRequest
import com.monh.packager.data.remote.auth.UserRepository
import com.monh.packager.utils.Event
import javax.inject.Inject

class ForgotPasswordViewModel @Inject constructor(private val userRepository: UserRepository): BaseViewModel() {

    val resetPasswordLiveData:MutableLiveData<Event<String>> = MutableLiveData()
    fun resetPassword(email:String){
        wrapBlockingOperation {
            handleResult(userRepository.resetPassword(ResetPasswordRequest(email = email))){
                resetPasswordLiveData.postValue(Event(it.data.message?:""))
            }
        }
    }
}
