package com.daeseong.alarm_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


class AlarmReceiver : BroadcastReceiver() {

    private val tag = AlarmReceiver::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {

        //호출후 삭제시 필요
        val nID = intent.extras!!.getInt("alarmID")
        Log.e(tag, "호출 시간 : " + getTimeDate() + " ID:" + nID)

        //activity에 전달
        val iID = Intent("com.daeseong.alarm_test.ID")
        iID.putExtra("alarmID", nID)
        context.sendBroadcast(iID)
    }

    private fun getTimeDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.format(Date())
    }
}