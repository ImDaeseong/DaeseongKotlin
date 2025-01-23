package com.daeseong.fcm_test

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingServiceEx : FirebaseMessagingService() {

    private val tag = FirebaseMessagingServiceEx::class.java.simpleName

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        try {

            val sTitle = remoteMessage.notification?.title?.takeIf { it.isNotBlank() } ?: ""
            val sMsg = remoteMessage.notification?.body?.takeIf { it.isNotBlank() } ?: ""
            Log.d(tag, "onMessageReceived Title: $sTitle, Message: $sMsg")

            when {
                remoteMessage.data.isNotEmpty() -> sendNotification(sTitle, sMsg, remoteMessage.data)
                else -> sendNotification(sTitle, sMsg)
            }

        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    override fun handleIntent(intent: Intent?) {
        intent?.extras?.let { bundle ->
            val remoteMessage = RemoteMessage(bundle)

            val sTitle = remoteMessage.notification?.title?.takeIf { it.isNotBlank() } ?: ""
            val sMsg = remoteMessage.notification?.body?.takeIf { it.isNotBlank() } ?: ""
            Log.d(tag, "handleIntent Title: $sTitle")

            if (remoteMessage.data.isNotEmpty()) {
                sendNotification(sTitle, sMsg, remoteMessage.data)
            }
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e(tag, "onNewToken: $token")
    }

    private fun sendNotification(sTitle: String, sMessage: String, data: Map<String, String>? = null) {

        data?.let {
            //Log.e(tag, "data: $it")
            it.forEach { (key, value) ->
                Log.e(tag, "$key: $value")
            }
        }

        //최초 설치시 토큰 들어옴
        if (data?.get("token") != null) {
            val token = data["token"]
            Log.d(tag, "sendNotification Token: $token")
            return
        }

        val intent = Intent(this, PushActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra("text1", "text1")
            putExtra("text2", "text2")
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, getString(R.string.channel_id))
            .setSmallIcon(R.drawable.ic_push_icons)
            .setContentTitle(sTitle)
            .setContentText(sMessage)
            .setStyle(NotificationCompat.BigTextStyle().bigText(sMessage))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, builder.build())
    }
}