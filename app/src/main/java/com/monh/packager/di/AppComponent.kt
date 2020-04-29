package com.monh.packager.di

import android.app.Application
import android.content.Context
import com.monh.packager.application.BaseApplication
import com.monh.packager.di.modules.ActivityModules
import com.monh.packager.di.modules.AppModule
import com.monh.packager.di.viewmodels.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityModules::class,
    AppModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}