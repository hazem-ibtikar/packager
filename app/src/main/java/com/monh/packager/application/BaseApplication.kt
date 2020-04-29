package com.monh.packager.application

import android.app.Application
import com.monh.packager.BuildConfig
import com.monh.packager.di.DaggerAppComponent
import com.monh.packager.utils.ReleaseTree
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject


class BaseApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .builder()
            .application(this)
            .context(this)
            .build()
            .inject(this)

        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(AndroidLogAdapter())

            Timber.plant(object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    Logger.log(priority, tag, message, t)
                }
            })
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}