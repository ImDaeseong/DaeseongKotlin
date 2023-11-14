package com.daeseong.alarm_test

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import java.text.SimpleDateFormat
import java.util.*

class AlarmNotificationReceiver : BroadcastReceiver() {

    private val tag = AlarmNotificationReceiver::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {

        // 호출 후 삭제시 필요
        val nID = intent.extras?.getInt("alarmID") ?: 0
        Log.e(tag, "호출 시간 : ${getTimeDate()} ID:$nID")

        // activity에 전달
        val iID = Intent("com.daeseong.alarm_test.ID")
        iID.putExtra("alarmID", nID)
        context.sendBroadcast(iID)

        val notifyID = 1
        val channelID = "daeseong_01"
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelID,"알림", NotificationManager.IMPORTANCE_DEFAULT)
            val notifyManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notifyManager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(context, channelID)
        } else {
            builder = NotificationCompat.Builder(context)
        }

        builder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("제목")
            .setContentText("내용")
            .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_SOUND)

        val notifyManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifyManager.notify(notifyID, builder.build())
    }

    private fun getTimeDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }
}
