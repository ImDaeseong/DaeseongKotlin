package com.daeseong.sensormanager_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LockActivity : AppCompatActivity() {

    private val tag: String = LockActivity::class.java.simpleName

    private var broadcastReceiver: BroadcastReceiver? = null
    private var intentFilter: IntentFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock)

        initFilter()
    }

    override fun onDestroy() {
        super.onDestroy()

        destroyFilter()
    }

    private fun initFilter() {

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    Intent.ACTION_SCREEN_OFF -> {
                        val item = Intent("com.daeseong.sensormanager_test.Screen").apply {
                            putExtra("screen", "폰화면 꺼짐")
                        }
                        context.sendBroadcast(item)
                    }
                    Intent.ACTION_SCREEN_ON -> {
                        val item = Intent("com.daeseong.sensormanager_test.Screen").apply {
                            putExtra("screen", "폰화면 켜짐")
                        }
                        context.sendBroadcast(item)
                    }
                }
            }
        }

        intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }

    private fun destroyFilter() {
        broadcastReceiver?.let {
            unregisterReceiver(it)
        }
    }
}
