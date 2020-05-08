package com.monh.packager.di.modules.fragment.modules

import com.monh.packager.ui.home.my_orders.MyOrdersContainerFragment
import com.monh.packager.ui.home.my_orders.cartons.CartonsFragment
import com.monh.packager.ui.home.my_orders.found_order.FoundOrderFragment
import com.monh.packager.ui.home.my_orders.order_details.OrderDetailsFragment
import com.monh.packager.ui.home.my_orders.order_preparation.OrderPreparationFragment
import com.monh.packager.ui.home.my_orders.orders_list.OrdersFragment
import com.monh.packager.ui.home.notifications.NotificationsFragment
import com.monh.packager.ui.home.settings.SettingsFragment
import com.monh.packager.ui.home.settings.change_language.ChangeLanguageFragment
import com.monh.packager.ui.home.settings.change_password.ChangePasswordFragment
import com.monh.packager.ui.home.settings.contactUs.ContactUsFragment
import com.monh.packager.ui.home.settings.edit_profile.EditProfileFragment
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

    @ContributesAndroidInjector
    abstract fun contributeOrderDetailsFragment(): OrderDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeOrderPreparationFragment(): OrderPreparationFragment

    @ContributesAndroidInjector
    abstract fun contributeFoundOrderFragment(): FoundOrderFragment

    @ContributesAndroidInjector
    abstract fun contributeCartonsFragment(): CartonsFragment

    @ContributesAndroidInjector
    abstract fun contributeChangeLanguageFragment(): ChangeLanguageFragment

}