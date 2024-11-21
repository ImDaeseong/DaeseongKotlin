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

    //앱이 실행중인 상태
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        runCatching {

            if (message.data.isNotEmpty()) {
                Log.e(tag, "데이터 메시지: ${message.data}")
                sendNotification(message.data)
            }

        }.onFailure { exception ->
            Log.e(tag, "Error", exception)
        }
    }

    //앱이 완전히 꺼진 상태에서 푸시메시지 전달받기
    override fun handleIntent(intent: Intent?) {

        intent?.extras?.let { bundle ->
            val remoteMessage = RemoteMessage(bundle)
            if (remoteMessage.data.isNotEmpty()) {
                sendNotification(remoteMessage.data)
            }
        }
    }

    private fun sendNotification(data: Map<String, String>) {

        //val data: Map<String, String> = message.getData()
        Log.e(tag, "data: $data")

        val sTitle = data["title"].orEmpty()
        val SMsg = data["body"].orEmpty()

        val mainActivityInstance = MainActivity.getInstance()
        val intent = mainActivityInstance?.let {
            Intent(this, PushActivity::class.java)
        } ?: Intent(this, MainActivity::class.java)

        intent.apply {
            putExtra("type", "1")
            putExtra("url", "https://m.naver.com")
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)  // 실제 앱 아이콘
            .setContentTitle(sTitle)
            .setContentText(SMsg)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())

        initBadge()
    }

    private fun initBadge() {

        val mainActivityInstance = MainActivity.getInstance()

        val badgeCount = if (mainActivityInstance != null) {

            //Log.e(tag, "MainActivity가 백그라운드 상태입니다. 뱃지 개수 1 증가")
            SharedPreferencesUtil.getValue(this, "BADGE", 0) + 1

        } else {
            //Log.e(tag, "MainActivity가 실행 중입니다. 뱃지 개수는 0")
            0
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
