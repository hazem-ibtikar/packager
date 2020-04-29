package com.monh.packager.ui.home.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import javax.inject.Inject

class NotificationsViewModel @Inject constructor() : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Notifications Fragment"
    }
    val text: LiveData<String> = _text
}