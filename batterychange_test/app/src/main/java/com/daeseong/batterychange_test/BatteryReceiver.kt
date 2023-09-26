package com.daeseong.batterychange_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BatteryReceiver : BroadcastReceiver() {

    private var batteryListener: BatteryListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val batteryLevel = it.getIntExtra("level", 0)
            batteryListener?.onListener(batteryLevel)
        }
    }

    interface BatteryListener {
        fun onListener(batteryLevel: Int)
    }

    fun setBatteryListener(batteryListener: BatteryListener?) {
        this.batteryListener = batteryListener
    }
}