package com.example.mvvmstarterproject.di.modules

import com.example.mvvmstarterproject.di.modules.fragment.modules.HomeActivityFragmentsProviders
import com.example.mvvmstarterproject.di.modules.fragment.modules.LoginActivityFragmentsProviders
import com.example.mvvmstarterproject.ui.auth.login.LoginActivity
import com.example.mvvmstarterproject.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModules {

    @ContributesAndroidInjector(modules = [HomeActivityFragmentsProviders::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [LoginActivityFragmentsProviders::class])
    abstract fun contributeLoginActivity(): LoginActivity
}