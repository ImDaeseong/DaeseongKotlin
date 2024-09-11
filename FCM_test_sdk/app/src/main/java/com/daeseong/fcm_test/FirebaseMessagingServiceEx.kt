package com.daeseong.fcm_test

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.Random

class FirebaseMessagingServiceEx : FirebaseMessagingService() {

    private val tag = FirebaseMessagingServiceEx::class.java.simpleName

    //lateinit var sTitle:String
    //lateinit var SMsg:String

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.e(tag, "onMessageReceived: $remoteMessage")

        try {

            val sTitle = remoteMessage.notification?.title ?: ""
            val SMsg = remoteMessage.notification?.body ?: ""

            Log.d(tag, "sTitle: $sTitle")
            Log.d(tag, "SMsg: $SMsg")

            if (remoteMessage.data.isEmpty()) {
                sendNotification(sTitle, SMsg)
            } else {
                sendNotification(sTitle, SMsg, remoteMessage.data)
            }

        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e(tag, "onNewToken: $token")
    }

    private fun sendNotification(sTitle: String, sMessage: String, data: Map<String, String>? = null) {

        data?.let {

            Log.e(tag, "data: $it")

            it["param1"]?.let { param1 -> Log.e(tag, "param1: $param1") }
            it["param2"]?.let { param2 -> Log.e(tag, "param2: $param2") }
        }

        val intent = Intent(this, PushActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent: PendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(this,0, intent,PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        } else {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val builder = NotificationCompat.Builder(this, getString(R.string.channel_id))
        builder.setSmallIcon(R.drawable.ic_push_icons)
        builder.setContentTitle(sTitle)
        builder.setContentText(sMessage)
        builder.setStyle(NotificationCompat.BigTextStyle().bigText(sMessage))
        builder.setContentIntent(pendingIntent)
        builder.setAutoCancel(true)

        val notificationId = Random().nextInt()
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(notificationId, builder.build())
    }
}