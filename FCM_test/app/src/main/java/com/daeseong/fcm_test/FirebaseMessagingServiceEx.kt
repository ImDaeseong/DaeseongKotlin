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

        //클릭시 호출
        val intent = Intent(this, PushActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0, intent,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)  // 실제 앱 아이콘
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())

        initBadge()
    }


    private fun initBadge() {

        val isRunning = MainActivity.getMainActivity() != null

        val badgeCount = if (isRunning) {
            //Log.e(tag, "MainActivity가 실행 중입니다. 뱃지 개수는 0")
            0
        } else {
            //Log.e(tag, "MainActivity가 백그라운드 상태입니다. 뱃지 개수 1 증가")
            SharedPreferencesUtil.getValue(this, "BADGE", 0) + 1
        }

        val intent = Intent("android.intent.action.BADGE_COUNT_UPDATE").apply {
            putExtra("badge_count", badgeCount)
            putExtra("badge_count_package_name", packageName)
            putExtra("badge_count_class_name", MainActivity::class.java.name)
        }
        sendBroadcast(intent)
        SharedPreferencesUtil.setValue(this, "BADGE", badgeCount)

    }

}
