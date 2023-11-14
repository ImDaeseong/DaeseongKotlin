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

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button

    private var alarmManager: AlarmManager? = null
    private var alarmPendingIntent: PendingIntent? = null
    private var alarmIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            setAlarm(true)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            setAlarm(false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyAlarm()
    }

    private fun setAlarm(isRepeat: Boolean) {

        destroyAlarm()

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, AlarmReceiver::class.java)
        val requestCode = Random().nextInt()
        alarmPendingIntent = PendingIntent.getBroadcast(this, requestCode, alarmIntent!!, PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, 5)

        if (isRepeat) {
            // 반복 설정
            alarmManager?.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 5000, alarmPendingIntent)
        } else {
            // 한번만 설정
            alarmManager?.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmPendingIntent)
        }

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formattedTime = simpleDateFormat.format(calendar.time)
        Log.e(tag, "설정된 알람 시간: $formattedTime")
    }

    private fun destroyAlarm() {
        if (alarmPendingIntent != null) {
            alarmManager?.cancel(alarmPendingIntent)
            alarmPendingIntent?.cancel()
            alarmManager = null
            alarmPendingIntent = null
        }
    }
}
