package com.monh.packager.ui.auth.forgot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import kotlinx.android.synthetic.main.forgot_password_fragment.*

class ForgotPasswordFragment : BaseFragment<ForgotPasswordViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forgot_password_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        handleBackPressed()
    }

    private fun handleBackPressed() {
        back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
