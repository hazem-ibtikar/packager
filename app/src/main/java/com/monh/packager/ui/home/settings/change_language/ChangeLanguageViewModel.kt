package com.monh.packager.ui.home.settings.change_language

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.auth.UserRepository
import com.monh.packager.utils.Event
import javax.inject.Inject

class ChangeLanguageViewModel @Inject constructor(private val userRepository: UserRepository): BaseViewModel() {
    var currentLang = ""
    val languageChangedSuccessfullyLiveData:MutableLiveData<Event<Boolean>> = MutableLiveData()
    fun getCurrentLanguage():String{
        return userRepository.getCurrentLanguage()
    }

    fun changeLanguage() {
        val oldLang = userRepository.getCurrentLanguage()
        if (oldLang == currentLang){
            languageChangedSuccessfullyLiveData.postValue(Event(true))
        } else{
            userRepository.handleLanguageChange(currentLang)
        }
    }
}
