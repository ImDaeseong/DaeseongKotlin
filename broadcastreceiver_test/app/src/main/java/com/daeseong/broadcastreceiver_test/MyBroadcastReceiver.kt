package com.daeseong.broadcastreceiver_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyBroadcastReceiver : BroadcastReceiver() {

    private val tag = MyBroadcastReceiver::class.java.simpleName

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == "android.intent.action.MyMessage") {
            val newIntent = Intent(context, Main1Activity::class.java)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(newIntent)
        }
    }
}
