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

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var bclicked1 : Boolean = false
    private var bclicked2 : Boolean = false

    private var alarmManager: AlarmManager? = null
    private var alarmpendingIntent: PendingIntent? = null
    private var AlarmID = 0
    private var alarmIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            if(bclicked1){
                cancelAlaram1();
                bclicked1 = false;
            }else {
                setAlaram1();
                bclicked1 = true;
            }
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            if(bclicked2){
                cancelAlaram2();
                bclicked2 = false;
            }else {
                setAlaram2();
                bclicked2 = true;
            }
        }

    }

    private fun setAlaram1() {

        AlarmID = 1
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, AlarmReceiver::class.java)
        alarmpendingIntent = PendingIntent.getBroadcast(this, AlarmID, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        if (alarmpendingIntent == null) {
            Log.e(tag, "설정된 알람이 없음")
        } else {
            Log.e(tag, "설정된 알람이 있음")
        }

        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 14)
        calendar.set(Calendar.MINUTE, 55)
        calendar.set(Calendar.SECOND, 0)
        alarmManager!!.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmpendingIntent)

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val sTime: String = simpleDateFormat.format(calendar.getTime())
        Log.e(tag, "1번 설정된 알람 시간 : $sTime")
    }

    private fun cancelAlaram1() {

        AlarmID = 1
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, AlarmReceiver::class.java)
        alarmpendingIntent = PendingIntent.getBroadcast(this, AlarmID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (alarmpendingIntent != null) {
            Log.e(tag, "1번 알람 취소")
            alarmManager!!.cancel(alarmpendingIntent)
            alarmpendingIntent!!.cancel()
        }
    }

    private fun setAlaram2() {

        AlarmID = 2
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, AlarmReceiver::class.java)
        alarmpendingIntent = PendingIntent.getBroadcast(this, AlarmID, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        if (alarmpendingIntent == null) {
            Log.e(tag, "설정된 알람이 없음")
        } else {
            Log.e(tag, "설정된 알람이 있음")
        }

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = 14
        calendar[Calendar.MINUTE] = 55
        calendar[Calendar.SECOND] = 0
        alarmManager!![AlarmManager.RTC_WAKEUP, calendar.timeInMillis] = alarmpendingIntent

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val sTime = simpleDateFormat.format(calendar.time)
        Log.e(tag, "2번 설정된 알람 시간 : $sTime")
    }

    private fun cancelAlaram2() {

        AlarmID = 2
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, AlarmReceiver::class.java)
        alarmpendingIntent = PendingIntent.getBroadcast(this, AlarmID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (alarmpendingIntent != null) {
            Log.e(tag, "2번 알람 취소")
            alarmManager!!.cancel(alarmpendingIntent)
            alarmpendingIntent!!.cancel()
        }
    }

}
