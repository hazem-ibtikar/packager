package com.monh.packager.ui.home.settings.change_password

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.auth.ChangePasswordRequest
import com.monh.packager.data.remote.auth.UserRepository
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    val passwordChangedSuccessfully:MutableLiveData<Boolean> = MutableLiveData()
    fun changePassword(oldPassword: String, newPassword: String) {
        wrapBlockingOperation {
            handleResult(userRepository.changePassword(ChangePasswordRequest(newPassword, oldPassword))){
                passwordChangedSuccessfully.postValue(true)
            }
        }
    }

}
