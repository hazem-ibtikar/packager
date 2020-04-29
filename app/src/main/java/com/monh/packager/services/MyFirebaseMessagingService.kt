package com.monh.packager.services

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.monh.packager.R
import com.monh.packager.data.locale.SharedPreferencesUtils
import com.monh.packager.ui.home.HomeActivity
import com.monh.packager.utils.NotificationUtils

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private var notificationUtils: NotificationUtils? = null
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage == null) return
        val message: Map<*, *> = remoteMessage.data
        notificationUtils = NotificationUtils(applicationContext)
        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.e(
                TAG,
                "Notification Body: " + remoteMessage.notification!!.body
            )
            val resultIntent = Intent(applicationContext, HomeActivity::class.java)
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            val sharedPreferencesUtils =
                SharedPreferencesUtils(applicationContext)
            notificationUtils!!.showNotificationMessage(
                resources.getString(R.string.app_name),
                remoteMessage.notification!!.body,
                resultIntent
            )
        } else {
        }
    }

    override fun onNewToken(token: String) {}

    companion object {
        private val TAG = MyFirebaseMessagingService::class.java.simpleName
    }
}