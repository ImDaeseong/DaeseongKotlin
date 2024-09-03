package com.daeseong.alarm_test

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class Main4Activity : AppCompatActivity() {

    private val tag = Main4Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private var isAlarm1Set: Boolean = false
    private var isAlarm2Set: Boolean = false

    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            if (isAlarm1Set) {
                cancelAlarm(1)
                isAlarm1Set = false
            } else {
                setAlarm(1, Calendar.HOUR_OF_DAY, 14, Calendar.MINUTE, 55, Calendar.SECOND, 0)
                isAlarm1Set = true
            }
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {

            if (isAlarm2Set) {
                cancelAlarm(2)
                isAlarm2Set = false
            } else {
                setAlarm(2, Calendar.HOUR_OF_DAY, 14, Calendar.MINUTE, 55, Calendar.SECOND, 0)
                isAlarm2Set = true
            }
        }
    }

    private fun setAlarm(alarmID: Int, hourField: Int, hour: Int, minuteField: Int, minute: Int, secondField: Int, second: Int) {

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(this, AlarmReceiver::class.java)
        val alarmPendingIntent = PendingIntent.getBroadcast(this, alarmID, alarmIntent,PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(hourField, hour)
            set(minuteField, minute)
            set(secondField, second)
        }

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmPendingIntent)

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val sTime = simpleDateFormat.format(calendar.time)
        Log.e(tag, "$alarmID 번 설정된 알람 시간 : $sTime")
    }

    private fun cancelAlarm(alarmID: Int) {

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(this, AlarmReceiver::class.java)
        val alarmPendingIntent = PendingIntent.getBroadcast(this, alarmID, alarmIntent,PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        Log.e(tag, "$alarmID 번 알람 취소")
        alarmManager.cancel(alarmPendingIntent)
        alarmPendingIntent.cancel()
    }
}
