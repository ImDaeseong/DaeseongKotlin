package com.daeseong.broadcastreceiver_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var broadcastReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService(Intent(this, ScreenOnOffService::class.java))

        registerBroadcastReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(broadcastReceiver)
    }

    private fun registerBroadcastReceiver() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val ntype = intent.extras!!.getInt("type")
                val sOn = intent.extras!!.getString("screen")
                Log.e(tag, "$ntype $sOn")
                sendCustomBroadcast()
            }
        }

        val intentFilter = IntentFilter().apply {
            addAction("com.daeseong.Screen")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(broadcastReceiver, intentFilter)
        }
    }

    private fun sendCustomBroadcast() {
        val intent = Intent(this, MyBroadcastReceiver::class.java)
        intent.action = "android.intent.action.MyMessage"
        sendBroadcast(intent)
    }
}
