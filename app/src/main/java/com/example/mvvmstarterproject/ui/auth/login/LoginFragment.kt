package com.example.mvvmstarterproject.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment
import com.example.mvvmstarterproject.ui.home.HomeActivity
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
            viewModel.signIn(email = emailET.text.toString(), password = passwordET.text.toString())
        }
    }

    private fun handleLoginResponse() {
        viewModel.loginSuccessfulLiveData.observe(viewLifecycleOwner, Observer {
            navigateToHome()
        })
    }

}
