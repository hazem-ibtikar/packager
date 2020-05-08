/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.monh.packager.di.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monh.packager.ui.auth.forgot.ForgotPasswordViewModel
import com.monh.packager.ui.auth.login.LoginActivityViewModel
import com.monh.packager.ui.auth.login.LoginViewModel
import com.monh.packager.ui.home.HomeViewModel
import com.monh.packager.ui.home.my_orders.MyOrdersContainerViewModel
import com.monh.packager.ui.home.my_orders.found_order.FoundOrderViewModel
import com.monh.packager.ui.home.my_orders.order_details.OrderDetailsViewModel
import com.monh.packager.ui.home.my_orders.order_preparation.OrderPreparationViewModel
import com.monh.packager.ui.home.my_orders.orders_list.OrdersViewModel
import com.monh.packager.ui.home.notifications.NotificationsViewModel
import com.monh.packager.ui.home.settings.SettingsViewModel
import com.monh.packager.ui.home.settings.change_language.ChangeLanguageViewModel
import com.monh.packager.ui.home.settings.change_password.ChangePasswordViewModel
import com.monh.packager.ui.home.settings.contactUs.ContactUsViewModel
import com.monh.packager.ui.home.settings.edit_profile.EditProfileViewModel
import com.monh.packager.ui.home.settings.terms.TermsAndConditionsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(OrdersViewModel::class)
    abstract fun bindOrdersViewModel(ordersViewModel: OrdersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyOrdersContainerViewModel::class)
    abstract fun bindHomeViewModel(myOrdersContainerViewModel: MyOrdersContainerViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeFrViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel::class)
    abstract fun bindNotificationsViewModel(notificationsViewModel: NotificationsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactUsViewModel::class)
    abstract fun bindContactUsViewModel(contactUsViewModel: ContactUsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    abstract fun bindEditProfileViewModel(editProfileViewModel: EditProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel::class)
    abstract fun bindChangePasswordViewModel(changePasswordViewModel: ChangePasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginActivityViewModel::class)
    abstract fun bindLoginActivityViewModel(loginActivityViewModel: LoginActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderDetailsViewModel::class)
    abstract fun bindOrderDetailsViewModel(orderDetailsViewModel: OrderDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderPreparationViewModel::class)
    abstract fun bindOrderPreparationViewModel(orderPreparationViewModel: OrderPreparationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FoundOrderViewModel::class)
    abstract fun bindFoundOrderViewModel(foundOrderViewModel: FoundOrderViewModel): ViewModel
    
    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel::class)
    abstract fun bindForgotPasswordViewModel(forgotPasswordViewModel: ForgotPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChangeLanguageViewModel::class)
    abstract fun bindChangeLanguageViewModel(changeLanguageViewModel: ChangeLanguageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TermsAndConditionsViewModel::class)
    abstract fun bindChangeTermsAndConditionsViewModel(termsAndConditionsViewModel: TermsAndConditionsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
