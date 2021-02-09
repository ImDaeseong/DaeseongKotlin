package com.daeseong.sensormanager_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null

    private var broadcastReceiver: BroadcastReceiver? = null
    private var intentFilter: IntentFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFilter()

        startService(Intent(this, ScreenOnOffService::class.java))

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            val intent = Intent(this, Main1Activity::class.java)
            startActivity(intent)
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener {

            val intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener {

            val intent = Intent(this, Main4Activity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        DestoryFilter()
    }

    private fun initFilter() {

        broadcastReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent) {
                if (intent.action == "com.daeseong.sensormanager_test.Screen") {
                    val sDesc = intent.extras!!.getString("screen")
                    Log.e(tag, sDesc)
                } else if (intent.action == "com.daeseong.sensormanager_test.Front") {
                    val sDesc = intent.extras!!.getString("front")
                    Log.e(tag, sDesc)
                }
            }
        }

        intentFilter = IntentFilter()
        intentFilter!!.addAction("com.daeseong.sensormanager_test.Screen")
        intentFilter!!.addAction("com.daeseong.sensormanager_test.Front")
        registerReceiver(broadcastReceiver, intentFilter)
    }

    private fun DestoryFilter() {

        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver)
        }
    }
}
