package com.daeseong.alarm_test

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class Main3Activity : AppCompatActivity() {

    private val tag = Main3Activity::class.java.simpleName

    private var alarmManager: AlarmManager? = null
    private var alarmPendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        setAlarm()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyAlarm()
    }

    private fun setAlarm() {
        destroyAlarm()

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 14)
            set(Calendar.MINUTE, 50)
            set(Calendar.SECOND, 0)
        }

        val alarmIntent = Intent(this, AlarmReceiver::class.java)
        val requestCode = 123

        alarmPendingIntent = PendingIntent.getBroadcast(this, requestCode, alarmIntent,PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        alarmManager?.let { manager ->
            alarmPendingIntent?.let { pendingIntent ->

                // 단일 알람 설정
                manager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

                /*
                // 1분마다 반복
                manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 1000 * 60 * 1, pendingIntent)
                */

                /*
                // 20분마다 반복
                manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 1000 * 60 * 20, pendingIntent)
                */

            } ?: run {
                Log.e(tag, "PendingIntent가 null입니다.")
            }
        } ?: run {
            Log.e(tag, "AlarmManager가 null입니다.")
        }

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val sTime = simpleDateFormat.format(calendar.time)
        Log.e(tag, "설정된 알람 시간: $sTime")
    }

    private fun destroyAlarm() {
        alarmPendingIntent?.let {
            alarmManager?.cancel(it)
            it.cancel()
        }
        alarmPendingIntent = null
        alarmManager = null
    }
}
