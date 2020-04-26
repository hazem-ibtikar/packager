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

package com.example.mvvmstarterproject.di.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmstarterproject.test.MainViewModel
import com.example.mvvmstarterproject.test.TestViewModelA
import com.example.mvvmstarterproject.ui.home.HomeViewModel
import com.example.mvvmstarterproject.ui.home.my_orders.MyOrdersContainerViewModel
import com.example.mvvmstarterproject.ui.home.my_orders.orders_list.OrdersViewModel
import com.example.mvvmstarterproject.ui.home.notifications.NotificationsViewModel
import com.example.mvvmstarterproject.ui.home.settings.SettingsViewModel
import com.example.mvvmstarterproject.ui.home.settings.change_password.ChangePasswordViewModel
import com.example.mvvmstarterproject.ui.home.settings.contactUs.ContactUsViewModel
import com.example.mvvmstarterproject.ui.home.settings.edit_profile.EditProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TestViewModelA::class)
    abstract fun bindTestFragmentAViewModel(mainViewModel: TestViewModelA): ViewModel

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
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
