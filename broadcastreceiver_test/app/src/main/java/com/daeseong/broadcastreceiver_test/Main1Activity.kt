package com.daeseong.broadcastreceiver_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class Main1Activity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var broadcastReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        registerBroadcastReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()

        destroyBroadcastReceiver()
    }

    private fun registerBroadcastReceiver() {

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    Intent.ACTION_SCREEN_OFF -> {
                        Log.e(tag, "폰화면 꺼짐")
                        sendScreenBroadcast(context, "off")
                    }
                    Intent.ACTION_SCREEN_ON -> {
                        Log.e(tag, "폰화면 켜짐")
                        sendScreenBroadcast(context, "on")
                    }
                }
            }
        }

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(broadcastReceiver, intentFilter)
        }
    }

    private fun destroyBroadcastReceiver() {
        broadcastReceiver?.let {
            unregisterReceiver(it)
        }
    }

    private fun sendScreenBroadcast(context: Context, screenState: String) {
        val item = Intent("com.daeseong.Screen").apply {
            putExtra("type", 2)
            putExtra("screen", screenState)
        }
        context.sendBroadcast(item)
    }
}
