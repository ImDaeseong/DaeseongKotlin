package com.daeseong.alarm_test

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class AlarmUtil private constructor() {

    private val tag = AlarmUtil::class.java.name

    companion object {
        private var instance: AlarmUtil? = null
        fun getInstance(): AlarmUtil {
            if (instance == null) {
                instance = AlarmUtil()
            }
            return instance as AlarmUtil
        }
    }

    private var alarmManager: AlarmManager? = null
    private var alarmPendingIntent: PendingIntent? = null
    private var context: Context? = null

    fun init(context: Context) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        this.context = context
    }

    fun addAlarm(nID: Int, nHour: Int, nMinute: Int) {

        //val alarmIntent = Intent(context, AlarmReceiver::class.java)
        val alarmIntent = Intent(context, AlarmNotificationReceiver::class.java)

        // 호출 후 삭제 위해 꼭 넘겨야 한다.
        alarmIntent.putExtra("alarmID", nID)
        alarmPendingIntent = PendingIntent.getBroadcast(context, nID, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = nHour
        calendar[Calendar.MINUTE] = nMinute
        calendar[Calendar.SECOND] = 0

        alarmManager?.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmPendingIntent)

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val sTime = simpleDateFormat.format(calendar.time)
        Log.e(tag, "설정된 알람 시간 : $sTime")
    }

    fun deleteAlarm(nID: Int) {

        //val alarmIntent = Intent(context, AlarmReceiver::class.java)
        val alarmIntent = Intent(context, AlarmNotificationReceiver::class.java)
        alarmPendingIntent = PendingIntent.getBroadcast(context, nID, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        alarmManager?.cancel(alarmPendingIntent)
        alarmPendingIntent?.cancel()
        Log.e(tag, "삭제된 알람 ID : $nID")
    }
}
