package com.daeseong.serviceboot_completed_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {

        val i = Intent()
        i.setClass(context, GameCheckService::class.java)
        context.startService(i)
    }
}