package com.daeseong.fcm_test

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PushActivity : AppCompatActivity() {

    private val tag = PushActivity::class.java.simpleName

    lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            finish()
        }

    }
}