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
    private var alarmpendingIntent: PendingIntent? = null
    private var alarmIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        setAlaram()
    }

    override fun onDestroy() {
        super.onDestroy()
        DestroyAlarm()
    }

    private fun setAlaram() {

        DestroyAlarm()

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, AlarmReceiver::class.java)
        alarmpendingIntent = PendingIntent.getBroadcast(this,0, alarmIntent,  PendingIntent.FLAG_UPDATE_CURRENT)

        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 14)
        calendar.set(Calendar.MINUTE, 50)
        calendar.set(Calendar.SECOND, 0)
        alarmManager!!.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmpendingIntent)

        //1분마다 반복
        //alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,1000 * 60 * 1,  alarmpendingIntent)

        //20분마다 반복
        //alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,1000 * 60 * 20, alarmpendingIntent)

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val sTime: String = simpleDateFormat.format(calendar.time)
        Log.e(tag, "설정된 알람 시간 : $sTime")
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
