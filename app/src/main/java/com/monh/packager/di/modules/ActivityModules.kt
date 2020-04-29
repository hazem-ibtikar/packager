package com.monh.packager.di.modules

import com.monh.packager.di.modules.fragment.modules.HomeActivityFragmentsProviders
import com.monh.packager.di.modules.fragment.modules.LoginActivityFragmentsProviders
import com.monh.packager.ui.auth.login.LoginActivity
import com.monh.packager.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModules {

    @ContributesAndroidInjector(modules = [HomeActivityFragmentsProviders::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [LoginActivityFragmentsProviders::class])
    abstract fun contributeLoginActivity(): LoginActivity
}