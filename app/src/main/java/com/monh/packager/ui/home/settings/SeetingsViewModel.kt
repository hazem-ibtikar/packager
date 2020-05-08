package com.monh.packager.ui.home.settings

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.auth.UserRepository
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {
    fun removeUserData() {
        userRepository.removeUserData()
    }

    fun getCurrentLang() :String{
        return userRepository.getCurrentLanguage()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is settings Fragment"
    }
}