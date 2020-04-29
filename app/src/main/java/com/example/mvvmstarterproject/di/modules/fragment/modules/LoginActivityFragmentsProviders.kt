package com.example.mvvmstarterproject.di.modules.fragment.modules

import com.example.mvvmstarterproject.ui.auth.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginActivityFragmentsProviders {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
}