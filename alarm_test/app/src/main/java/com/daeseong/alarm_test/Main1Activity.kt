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

    private val TAG = Main1Activity::class.java.simpleName

    private var button1: Button? = null
    private var button2:Button? = null

    private var alarmManager: AlarmManager? = null
    private var alarmpendingIntent: PendingIntent? = null
    private var alarmIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            setAlaram(false)
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            setAlaram(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DestroyAlarm()
    }

    private fun setAlaram(bRepeat: Boolean) {

        if (bRepeat) {

            DestroyAlarm()

            // 반복
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmIntent = Intent(this, AlarmReceiver::class.java)
            alarmpendingIntent = PendingIntent.getBroadcast(this,0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.SECOND, 5)
            alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,5000, alarmpendingIntent)

            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val sTime = simpleDateFormat.format(calendar.time)
            Log.e(TAG, "설정된 알람 시간 : $sTime")

        } else {

            DestroyAlarm()

            // 1회용
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmIntent = Intent(this, AlarmReceiver::class.java)
            alarmpendingIntent = PendingIntent.getBroadcast(this,0,alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT)

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.SECOND, 5)
            alarmManager!![AlarmManager.RTC_WAKEUP, calendar.timeInMillis] = alarmpendingIntent

            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val sTime = simpleDateFormat.format(calendar.time)
            Log.e(TAG, "설정된 알람 시간 : $sTime")
        }
    }

    private fun DestroyAlarm() {

        if (alarmpendingIntent != null) {

            alarmpendingIntent = PendingIntent.getBroadcast(this,0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
            alarmManager!!.cancel(alarmpendingIntent)
            alarmpendingIntent!!.cancel()
            alarmManager = null
            alarmpendingIntent = null
        }
    }
}
