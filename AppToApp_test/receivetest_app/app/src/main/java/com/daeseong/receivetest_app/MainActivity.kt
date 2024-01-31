package com.daeseong.receivetest_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var tv1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1 = findViewById(R.id.tv1)

        try {

            // 다른 앱에서 전달받은 파라미터
            val intent: Intent? = intent
            if (intent != null) {
                val sParam: String? = intent.getStringExtra("param")
                tv1.text = sParam
            }

        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }
}