package com.monh.packager.di.modules

import com.monh.packager.services.MyFirebaseMessagingService
import dagger.Module
import dagger.android.ContributesAndroidInjector




@Module
abstract class ServicesModule {
    @ContributesAndroidInjector
    abstract fun contributeMyFirebaseService(): MyFirebaseMessagingService
}