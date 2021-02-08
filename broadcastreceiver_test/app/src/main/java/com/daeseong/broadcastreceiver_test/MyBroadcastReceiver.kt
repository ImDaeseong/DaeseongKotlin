package com.daeseong.broadcastreceiver_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyBroadcastReceiver : BroadcastReceiver() {

    private val tag = MyBroadcastReceiver::class.java.simpleName

    override fun onReceive(context: Context?, intent: Intent?) {

        //Log.e(tag, "onReceive:" + intent!!.action)

        if (intent!!.action.equals("android.intent.action.MyMessage")) {

            val newIntent = Intent(context, Main1Activity::class.java)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(newIntent)
        }
    }
}