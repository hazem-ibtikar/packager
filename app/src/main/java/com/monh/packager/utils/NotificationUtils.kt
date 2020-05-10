package com.monh.packager.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.monh.packager.R
import java.util.*
import javax.inject.Inject

class NotificationUtils @Inject constructor(private val mContext: Context) {
    fun showNotificationMessage(
        title: String?,
        message: String?,
        intent: Intent?
    ) {
        val resultPendingIntent = PendingIntent.getActivity(
            mContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val CHANNEL_ID =
            mContext.resources.getString(R.string.default_notification_channel_id)
        val channelName =
            mContext.resources.getString(R.string.default_notification_channel_name)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                mContext.getSystemService(
                    NotificationManager::class.java
                )
            notificationManager?.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID,
                    channelName, NotificationManager.IMPORTANCE_HIGH
                )
            )
            val mBuilder =
                NotificationCompat.Builder(mContext, CHANNEL_ID)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText(message)
            mBuilder.setContentIntent(resultPendingIntent)
            notificationManager?.notify(
                uniqueNotificationId,
                mBuilder.build()
            )
        } else {
            val inboxStyle =
                NotificationCompat.InboxStyle()
            val mBuilder =
                NotificationCompat.Builder(
                    mContext, CHANNEL_ID
                )
            val notification: Notification
            notification = mBuilder.setTicker(title).setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentIntent(resultPendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.ic_bell)
                .setContentText(message)
                .build()
            val notificationManager =
                mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
            notificationManager.notify(
                uniqueNotificationId,
                notification
            )
        }
    }

    companion object {
        private val TAG = NotificationUtils::class.java.simpleName
        val uniqueNotificationId: Int
            get() = (Date().time / 1000L % Int.MAX_VALUE).toInt()
    }

}