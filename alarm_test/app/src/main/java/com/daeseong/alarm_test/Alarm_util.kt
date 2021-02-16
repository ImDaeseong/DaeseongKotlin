package com.daeseong.alarm_test

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


class Alarm_util {

    private val tag = Alarm_util::class.java.name

    companion object {
        private var instance: Alarm_util? = null
        fun getInstance(): Alarm_util {
            if (instance == null) {
                instance = Alarm_util()
            }
            return instance as Alarm_util
        }
    }

    private var alarmManager: AlarmManager? = null
    private var alarmpendingIntent: PendingIntent? = null
    private var mcontext: Context? = null
    private val calendar: Calendar? = null

    fun init(context: Context) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        mcontext = context
    }

    fun AddAlaram(nID: Int, nHour: Int, nMinute: Int) {

        //Intent alarmIntent = new Intent(mcontext, AlarmReceiver.class);
        val alarmIntent = Intent(mcontext, AlarmNotificationReceiver::class.java)

        //호출후 삭제 위해 꼭 넘겨야 한다.
        alarmIntent.putExtra("alarmID", nID)
        alarmpendingIntent = PendingIntent.getBroadcast(mcontext, nID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = nHour
        calendar[Calendar.MINUTE] = nMinute
        calendar[Calendar.SECOND] = 0

        alarmManager!![AlarmManager.RTC_WAKEUP, calendar.timeInMillis] = alarmpendingIntent

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val sTime = simpleDateFormat.format(calendar.time)
        Log.e(tag, "설정된 알람 시간 : $sTime")
    }

    fun DeleteAlarm(nID: Int) {

        //Intent alarmIntent = new Intent(mcontext, AlarmReceiver.class);
        val alarmIntent = Intent(mcontext, AlarmNotificationReceiver::class.java)
        alarmpendingIntent = PendingIntent.getBroadcast(mcontext, nID, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager!!.cancel(alarmpendingIntent)
        alarmpendingIntent!!.cancel()
        Log.e(tag, "삭제된 알람 ID : $nID")
    }

}