package com.daeseong.broadcastreceiver_test

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log


class ScreenOnOffService : Service() {

    private val tag = ScreenOnOffService::class.java.simpleName

    private var broadcastReceiver: BroadcastReceiver? = null
    private var intentFilter: IntentFilter? = null

    override fun onCreate() {
        super.onCreate()
        initBroadcastReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()
        DestoryBroadcastReceiver()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun initBroadcastReceiver() {

        broadcastReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {

                if (intent.action == Intent.ACTION_SCREEN_OFF) {

                    //Log.e(tag, "폰화면 꺼짐")
                    val item = Intent("com.daeseong.Screen")
                    item.putExtra("type", 1)
                    item.putExtra("screen", "off")
                    context.sendBroadcast(item)

                } else if (intent.action == Intent.ACTION_SCREEN_ON) {

                    //Log.e(tag, "폰화면 켜짐")
                    val item = Intent("com.daeseong.Screen")
                    item.putExtra("type", 1)
                    item.putExtra("screen", "on")
                    context.sendBroadcast(item)

                }
            }

        }
        intentFilter = IntentFilter()
        intentFilter!!.addAction(Intent.ACTION_SCREEN_ON)
        intentFilter!!.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(broadcastReceiver, intentFilter)
    }

    private fun DestoryBroadcastReceiver() {

        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver)
        }
    }
}