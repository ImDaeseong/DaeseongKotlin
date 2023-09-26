package com.daeseong.batterychange_test

import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.batterychange_test.BatteryReceiver.BatteryListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var mReceiver: BatteryReceiver? = null

    private lateinit var tvBattery: TextView
    private lateinit var floatBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvBattery = findViewById(R.id.tvbattery)
        floatBtn = findViewById(R.id.floatbtn)

        floatBtn.setOnClickListener {
            Toast.makeText(this, "FloatingActionButton click", Toast.LENGTH_SHORT).show()
        }

        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        mReceiver = BatteryReceiver()
        registerReceiver(mReceiver, intentFilter)

        mReceiver!!.setBatteryListener(object : BatteryListener {
            override fun onListener(batteryLevel: Int) {
                setBatteryInfo(batteryLevel)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mReceiver)
    }

    private fun setBatteryInfo(batteryLevel: Int) {

        val textColor = when {
            batteryLevel >= 50 -> Color.GREEN
            batteryLevel >= 30 -> Color.YELLOW
            else -> Color.RED
        }

        tvBattery.setTextColor(textColor)
        tvBattery.text  = String.format("밧데리 잔량:%d%%", batteryLevel)
    }
}
