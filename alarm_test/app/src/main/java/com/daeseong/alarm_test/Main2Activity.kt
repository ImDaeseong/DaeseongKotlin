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
    private var alarmPendingIntent: PendingIntent? = null

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

        alarmPendingIntent = PendingIntent.getBroadcast(this,0, alarmIntent,PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            add(Calendar.SECOND, 5)
        }

        alarmManager?.let { manager ->
            alarmPendingIntent?.let { pendingIntent ->
                manager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            } ?: run {
                Log.e(tag, "PendingIntent가 null입니다.")
            }
        } ?: run {
            Log.e(tag, "AlarmManager가 null입니다.")
        }

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formattedTime = simpleDateFormat.format(calendar.time)
        Log.e(tag, "설정된 알람 시간 : $formattedTime")
    }

    private fun destroyAlarm() {
        alarmPendingIntent?.let {
            alarmManager?.cancel(it)
            it.cancel()
            alarmPendingIntent = null
        }
    }
}
