package com.example.mvvmstarterproject.ui.auth.login
import android.os.Bundle
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseActivity
import kotlinx.android.synthetic.main.app_bar_home.*

class LoginActivity : BaseActivity<LoginActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
