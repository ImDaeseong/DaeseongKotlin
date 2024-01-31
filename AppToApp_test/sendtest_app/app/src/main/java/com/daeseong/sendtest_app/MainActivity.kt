package com.daeseong.sendtest_app

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var et1: EditText
    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et1 = findViewById(R.id.et1)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            // 다른 앱에서 파라미터 전달
            val sParam = et1.text.toString()
            val intent = Intent().apply {
                component = ComponentName("com.daeseong.receivetest_app", "com.daeseong.receivetest_app.MainActivity")
                putExtra("param", sParam)
            }
            startActivity(intent)
        }
    }
}