package com.daeseong.fcm_test

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingServiceEx : FirebaseMessagingService() {

    private val tag = FirebaseMessagingService::class.java.simpleName

    private val channelId by lazy { getString(R.string.default_notification_channel_id) }

    lateinit var sTitle:String
    lateinit var SMsg:String

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        try {

            if (message.data.isNotEmpty()) {
                Log.d(tag, "데이터 메시지: ${message.data}")
            }

            if (message.notification != null) {
                sTitle = message.notification?.title.toString()
                SMsg = message.notification?.body.toString()
                Log.e(tag, "getTitle:$SMsg")
                Log.e(tag, "getBody:$SMsg")
                sendNotification(sTitle, SMsg)
            }

        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    private fun sendNotification(title: String, message: String) {
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)  // 실제 앱 아이콘
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}
