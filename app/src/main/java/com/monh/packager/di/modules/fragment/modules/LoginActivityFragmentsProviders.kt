package com.monh.packager.di.modules.fragment.modules

import com.monh.packager.ui.auth.forgot.ForgotPasswordFragment
import com.monh.packager.ui.auth.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginActivityFragmentsProviders {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
    @ContributesAndroidInjector
    abstract fun contributeForgotPasswordFragment(): ForgotPasswordFragment

}