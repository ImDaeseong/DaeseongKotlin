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

    override fun onCreate() {
        super.onCreate()
        registerBroadcastReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyBroadcastReceiver()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun registerBroadcastReceiver() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    Intent.ACTION_SCREEN_OFF -> {
                        Log.e(tag, "폰화면 꺼짐")
                        sendScreenBroadcast(context, "off", 1)
                    }
                    Intent.ACTION_SCREEN_ON -> {
                        Log.e(tag, "폰화면 켜짐")
                        sendScreenBroadcast(context, "on", 1)
                    }
                }
            }
        }
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }

    private fun destroyBroadcastReceiver() {
        broadcastReceiver?.let {
            unregisterReceiver(it)
        }
    }

    private fun sendScreenBroadcast(context: Context, screenState: String, type: Int) {
        val item = Intent("com.daeseong.Screen").apply {
            putExtra("type", type)
            putExtra("screen", screenState)
        }
        context.sendBroadcast(item)
    }
}
