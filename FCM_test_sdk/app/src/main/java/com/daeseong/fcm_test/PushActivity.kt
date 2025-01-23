package com.daeseong.fcm_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class PushActivity : AppCompatActivity() {

    private val tag = PushActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push)

        intent.extras?.let { pushData ->
            val text1 = pushData.getString("text1").orEmpty()
            val text2 = pushData.getString("text2").orEmpty()
            Log.e(tag, "text1:$text1")
            Log.e(tag, "text2:$text2")
        }
    }
}