package com.example.mvvmstarterproject.ui.home.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmstarterproject.base.BaseViewModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor() : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is settings Fragment"
    }
    val text: LiveData<String> = _text
}