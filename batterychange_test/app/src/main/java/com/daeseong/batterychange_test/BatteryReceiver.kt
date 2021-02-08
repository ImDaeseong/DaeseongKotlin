package com.daeseong.batterychange_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class BatteryReceiver : BroadcastReceiver() {

    var batteryListener: BatteryListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {

        val batteryLevel = intent!!.getIntExtra("level", 0)
        batteryListener!!.onListener(batteryLevel)
    }

    interface BatteryListener {
        fun onListener(batteryLevel: Int)
    }

    @JvmName("setBatteryListener1")
    fun setBatteryListener(batteryListener: BatteryListener?) {
        this.batteryListener = batteryListener
    }
}