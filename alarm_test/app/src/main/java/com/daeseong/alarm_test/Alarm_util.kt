package com.daeseong.alarm_test

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class AlarmUtil private constructor() {

    private val tag = AlarmUtil::class.java.simpleName

    companion object {
        @Volatile
        private var instance: AlarmUtil? = null

        fun getInstance(): AlarmUtil {
            return instance ?: synchronized(this) {
                instance ?: AlarmUtil().also { instance = it }
            }
        }
    }

    private var alarmManager: AlarmManager? = null
    private var context: Context? = null

    // 초기화 메서드
    fun init(context: Context) {
        this.context = context.applicationContext
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
    }

    // 알람 추가 메서드
    fun addAlarm(nID: Int, nHour: Int, nMinute: Int) {
        val context = context ?: run {
            Log.e(tag, "Context가 null입니다. AlarmUtil을 먼저 초기화하세요.")
            return
        }

        // Android 12 (API 31) 이상에서 정확한 알람 설정 권한 확인
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager?.canScheduleExactAlarms() != true) {
                Log.e(tag, "정확한 알람을 설정할 권한이 없습니다. 권한 요청이 필요합니다.")
                requestScheduleExactAlarmPermission()
                return
            }
        }

        val alarmIntent = Intent(context, AlarmNotificationReceiver::class.java).apply {
            putExtra("alarmID", nID)
        }

        val alarmPendingIntent = PendingIntent.getBroadcast(
            context, nID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, nHour)
            set(Calendar.MINUTE, nMinute)
            set(Calendar.SECOND, 0)
        }

        alarmManager?.let { manager ->
            if (alarmPendingIntent != null) {
                manager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmPendingIntent)
                Log.d(tag, "알람이 설정되었습니다: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(calendar.time)}")
            } else {
                Log.e(tag, "PendingIntent가 null입니다.")
            }
        } ?: Log.e(tag, "AlarmManager가 null입니다.")
    }

    // 알람 삭제 메서드
    fun deleteAlarm(nID: Int) {
        val context = context ?: run {
            Log.e(tag, "Context가 null입니다. AlarmUtil을 먼저 초기화하세요.")
            return
        }

        val alarmIntent = Intent(context, AlarmNotificationReceiver::class.java)
        val alarmPendingIntent = PendingIntent.getBroadcast(
            context, nID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager?.let { manager ->
            if (alarmPendingIntent != null) {
                manager.cancel(alarmPendingIntent)
                Log.d(tag, "알람 ID 삭제되었습니다.")
            } else {
                Log.e(tag, "PendingIntent가 null입니다.")
            }
        } ?: Log.e(tag, "AlarmManager가 null입니다.")
    }

    // 알람 권한(Android 12 이상)
    @RequiresApi(Build.VERSION_CODES.S)
    private fun requestScheduleExactAlarmPermission() {
        val context = context ?: return
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}
