package com.monh.packager.ui.home.settings

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.auth.UserRepository
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {
    val loggedOutSuccessfully:MutableLiveData<Boolean> = MutableLiveData()
    fun removeUserData() {
        userRepository.removeUserData()
    }

    fun getCurrentLang() :String{
        return userRepository.getCurrentLanguage()
    }

    fun logOut(uuid:String){
        wrapBlockingOperation {
            handleResult(userRepository.logOut(uuid)){
                removeUserData()
                loggedOutSuccessfully.postValue(true)
            }
        }
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is settings Fragment"
    }
}