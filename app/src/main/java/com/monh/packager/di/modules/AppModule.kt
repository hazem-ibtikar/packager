package com.monh.packager.di.modules

import android.content.Context
import com.monh.packager.BuildConfig
import com.monh.packager.data.locale.SharedPreferencesUtils
import com.monh.packager.utils.ConnectivityUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class AppModule {

    @Provides
    fun provideIsDebug() = BuildConfig.DEBUG

    @Provides
    @Singleton
    fun provideConnectivityUtils(context: Context): ConnectivityUtils = ConnectivityUtils(context)

    @Provides
    @Singleton
    fun provideSharedPrefs(context: Context): SharedPreferencesUtils = SharedPreferencesUtils(context)

}