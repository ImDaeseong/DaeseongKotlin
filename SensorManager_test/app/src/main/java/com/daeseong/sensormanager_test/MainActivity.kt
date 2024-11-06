package com.daeseong.sensormanager_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    private var broadcastReceiver: BroadcastReceiver? = null
    private var intentFilter: IntentFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFilter()

        startService(Intent(this, ScreenOnOffService::class.java))

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            startActivity(Intent(this, Main1Activity::class.java))
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            startActivity(Intent(this, Main3Activity::class.java))
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {
            startActivity(Intent(this, Main4Activity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyFilter()
    }

    private fun initFilter() {

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                when (intent.action) {
                    "com.daeseong.sensormanager_test.Screen" -> {
                        val sDesc = intent.extras?.getString("screen")
                        Log.e(tag, sDesc.orEmpty())
                    }
                    "com.daeseong.sensormanager_test.Front" -> {
                        val sDesc = intent.extras?.getString("front")
                        Log.e(tag, sDesc.orEmpty())
                    }
                }
            }
        }

        intentFilter = IntentFilter().apply {
            addAction("com.daeseong.sensormanager_test.Screen")
            addAction("com.daeseong.sensormanager_test.Front")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED)
        } else {
            registerReceiver(broadcastReceiver, intentFilter)
        }
    }

    private fun destroyFilter() {
        broadcastReceiver?.let {
            unregisterReceiver(it)
        }
    }
}
