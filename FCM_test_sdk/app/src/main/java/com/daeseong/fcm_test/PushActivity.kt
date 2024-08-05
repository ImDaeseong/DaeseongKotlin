package com.daeseong.fcm_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class PushActivity : AppCompatActivity() {

    private val tag = PushActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push)


        val pushData = intent.extras
        if (pushData != null) {

            val sTitle = pushData.getString("sTitle")
            if (sTitle != null) {
                Log.e(tag, sTitle)
            }

            val sMessage = pushData.getString("sMessage")
            if (sMessage != null) {
                Log.e(tag, sMessage)
            }
        }

    }
}