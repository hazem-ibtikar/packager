package com.monh.packager.ui.home.settings.change_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.monh.packager.R
import com.monh.packager.base.BaseFragment

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
