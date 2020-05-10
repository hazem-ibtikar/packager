package com.monh.packager.services

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.monh.packager.R
import com.monh.packager.data.locale.SharedPreferencesUtils
import com.monh.packager.data.remote.auth.UserRepository
import com.monh.packager.ui.home.HomeActivity
import com.monh.packager.ui.home.ORDER_ID
import com.monh.packager.utils.NotificationUtils
import dagger.android.AndroidInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MyFirebaseMessagingService : FirebaseMessagingService() {
    @Inject
    lateinit var notificationUtils: NotificationUtils

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate() {
        super.onCreate()
        AndroidInjection.inject(this);
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage == null) return
        val message: Map<*, *> = remoteMessage.data
        val resultIntent = Intent(applicationContext, HomeActivity::class.java)
        resultIntent.putExtra(ORDER_ID, remoteMessage.data["orderId"]?.toInt())
        resultIntent.action = Intent.ACTION_MAIN;
        resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        val sharedPreferencesUtils =
            SharedPreferencesUtils(applicationContext)
        notificationUtils.showNotificationMessage(
            resources.getString(R.string.app_name),
            remoteMessage.notification?.body?:remoteMessage.data["body"],
            resultIntent
        )
    }

    override fun onNewToken(token: String) {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d("TAG", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID).let {
                    GlobalScope.launch(Dispatchers.IO){
                        userRepository.updateUserToken(token?:"", it)
                    }
                }

            })
    }

    companion object {
        private val TAG = MyFirebaseMessagingService::class.java.simpleName
    }
}