package com.monh.packager.ui.home.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor() : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is settings Fragment"
    }
    val text: LiveData<String> = _text
}