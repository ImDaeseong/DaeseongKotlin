package com.daeseong.timer_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            startActivity(Intent(this, Timer1Activity::class.java))
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            startActivity(Intent(this, Timer2Activity::class.java))
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            startActivity(Intent(this, Timer3Activity::class.java))
        }
    }

}
