package com.daeseong.alarm_test

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class Main2Activity : AppCompatActivity() {

    private val tag = Main2Activity::class.java.simpleName

    private var alarmManager: AlarmManager? = null
    private var alarmpendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        setAlarm()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyAlarm()
    }

    private fun setAlarm() {
        destroyAlarm()

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(this, AlarmReceiver::class.java)
        alarmpendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, 5)

        alarmManager?.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmpendingIntent)

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formattedTime = simpleDateFormat.format(calendar.time)
        Log.e(tag, "설정된 알람 시간 : $formattedTime")
    }

    private fun destroyAlarm() {
        if (alarmpendingIntent != null) {
            alarmManager?.cancel(alarmpendingIntent)
            alarmpendingIntent?.cancel()
            alarmManager = null
            alarmpendingIntent = null
        }
    }
}
