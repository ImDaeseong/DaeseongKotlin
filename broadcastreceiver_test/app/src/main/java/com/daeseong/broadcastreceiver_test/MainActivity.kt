package com.daeseong.broadcastreceiver_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var broadcastReceiver: BroadcastReceiver? = null
    private var intentFilter: IntentFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBroadcastReceiver()

        startService(Intent(this, ScreenOnOffService::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()

        DestoryBroadcastReceiver()
    }

    private fun initBroadcastReceiver() {

        broadcastReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                val ntype = intent.extras!!.getInt("type")
                val sOn = intent.extras!!.getString("screen")
                Log.e(tag, "$ntype $sOn")
                SendBroadcast()
            }
        }
        intentFilter = IntentFilter()
        intentFilter!!.addAction("com.daeseong.Screen")
        registerReceiver(broadcastReceiver, intentFilter)
    }

    private fun DestoryBroadcastReceiver() {

        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver)
        }
    }

    private fun SendBroadcast() {

        val intent = Intent(this, MyBroadcastReceiver::class.java)
        intent.action = "android.intent.action.MyMessage"
        sendBroadcast(intent)
    }
}
