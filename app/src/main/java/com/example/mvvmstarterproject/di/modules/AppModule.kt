package com.example.mvvmstarterproject.di.modules

import android.content.Context
import android.os.Build
import com.example.mvvmstarterproject.BuildConfig
import com.example.mvvmstarterproject.data.locale.SharedPreferencesUtils
import com.example.mvvmstarterproject.utils.ConnectivityUtils
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