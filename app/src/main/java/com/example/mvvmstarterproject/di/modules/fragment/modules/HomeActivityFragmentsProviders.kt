package com.example.mvvmstarterproject.di.modules.fragment.modules

import com.example.mvvmstarterproject.ui.home.my_orders.MyOrdersContainerFragment
import com.example.mvvmstarterproject.ui.home.my_orders.orders_list.OrdersFragment
import com.example.mvvmstarterproject.ui.home.notifications.NotificationsFragment
import com.example.mvvmstarterproject.ui.home.settings.SettingsFragment
import com.example.mvvmstarterproject.ui.home.settings.change_password.ChangePasswordFragment
import com.example.mvvmstarterproject.ui.home.settings.contactUs.ContactUsFragment
import com.example.mvvmstarterproject.ui.home.settings.edit_profile.EditProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityFragmentsProviders {
    @ContributesAndroidInjector
    abstract fun contributeOrdersFragment(): OrdersFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): MyOrdersContainerFragment

    @ContributesAndroidInjector
    abstract fun contributeNotificationsFragment(): NotificationsFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun contributeContactUsFragment(): ContactUsFragment

    @ContributesAndroidInjector
    abstract fun contributeEditProfileFragment(): EditProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeChangePasswordFragment(): ChangePasswordFragment
}