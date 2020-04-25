package com.example.mvvmstarterproject.ui.home.my_orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmstarterproject.base.BaseViewModel
import javax.inject.Inject

class MyOrdersContainerViewModel @Inject constructor(): BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}