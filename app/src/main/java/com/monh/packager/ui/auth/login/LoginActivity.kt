package com.monh.packager.ui.auth.login
import android.os.Bundle
import com.monh.packager.R
import com.monh.packager.base.BaseActivity

class LoginActivity : BaseActivity<LoginActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
