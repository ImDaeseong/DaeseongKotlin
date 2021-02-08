package com.daeseong.sensormanager_test

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder


class ScreenOnOffService : Service() {

    private val tag = ScreenOnOffService::class.java.simpleName

    private var broadcastReceiver: BroadcastReceiver? = null
    private var intentFilter: IntentFilter? = null

    override fun onCreate() {
        super.onCreate()
        initFilter()
    }

    override fun onDestroy() {
        super.onDestroy()
        DestoryFilter()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun initFilter() {

        broadcastReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == Intent.ACTION_SCREEN_OFF) {
                    val item = Intent("com.daeseong.sensormanager_test.Screen")
                    item.putExtra("screen", "폰화면 꺼짐")
                    context.sendBroadcast(item)
                } else if (intent.action == Intent.ACTION_SCREEN_ON) {
                    val item = Intent("com.daeseong.sensormanager_test.Screen")
                    item.putExtra("screen", "폰화면 켜짐")
                    context.sendBroadcast(item)
                }
            }
        }
        intentFilter = IntentFilter()
        intentFilter!!.addAction(Intent.ACTION_SCREEN_ON)
        intentFilter!!.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(broadcastReceiver, intentFilter)
    }

    private fun DestoryFilter() {
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver)
        }
    }
}