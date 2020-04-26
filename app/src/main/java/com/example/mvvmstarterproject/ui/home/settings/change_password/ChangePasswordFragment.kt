package com.example.mvvmstarterproject.ui.home.settings.change_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment

class ChangePasswordFragment : BaseFragment<ChangePasswordViewModel>() {

    companion object {
        fun newInstance() = ChangePasswordFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.change_password_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
