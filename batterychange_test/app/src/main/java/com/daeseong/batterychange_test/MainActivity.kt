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

    private var tvbattery: TextView? = null
    private var floatbtn: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvbattery = findViewById<TextView>(R.id.tvbattery)


        floatbtn = findViewById<FloatingActionButton>(R.id.floatbtn)
        floatbtn!!.setOnClickListener {
            Toast.makeText(this, "FloatingActionButton click", Toast.LENGTH_SHORT).show()
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        mReceiver = BatteryReceiver()
        registerReceiver(mReceiver, intentFilter)

        mReceiver!!.batteryListener = object : BatteryListener {
            override fun onListener(batteryLevel: Int) {
                setBatteryInfo(batteryLevel)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mReceiver)
    }

    private fun setBatteryInfo(batteryLevel: Int) {

        if (batteryLevel >= 50) {
            tvbattery!!.setTextColor(Color.GREEN)
        }
        else if (batteryLevel >= 30) {
            tvbattery!!.setTextColor(Color.YELLOW)
        }
        else {
            tvbattery!!.setTextColor(Color.RED)
        }

        tvbattery!!.text = String.format("밧데리 잔량:%d%%", batteryLevel)
    }

}
