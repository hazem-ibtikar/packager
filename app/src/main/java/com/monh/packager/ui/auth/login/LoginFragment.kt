package com.monh.packager.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.ui.home.HomeActivity
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseFragment<LoginViewModel>() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        checkIfLoggedInUser()
        handleLoginResponse()
        handleLoginRequest()
    }

    private fun checkIfLoggedInUser() {
        viewModel.checkIfUserLoggedIn().let {
            if (it){
                navigateToHome()
            }
        }
    }

    private fun navigateToHome() {
        activity?.startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()
    }

    private fun handleLoginRequest() {
        signInBtn.setOnClickListener {
            if (emailET.text.toString().isBlank()){
                emailET.error = context?.getString(R.string.email_empty_validation)
                return@setOnClickListener
            }
            if (passwordET.text.toString().isBlank()){
                passwordET.error = context?.getString(R.string.password_empty_validation)
                return@setOnClickListener
            }
            viewModel.signIn(email = emailET.text.toString(), password = passwordET.text.toString())
        }
    }

    private fun handleLoginResponse() {
        viewModel.loginSuccessfulLiveData.observe(viewLifecycleOwner, Observer {
            navigateToHome()
        })
    }

}
