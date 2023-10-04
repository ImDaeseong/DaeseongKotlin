package com.daeseong.handlerthread_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        button1.setOnClickListener {
            startActivity(Intent(this, Main1Activity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }

        button3.setOnClickListener {
            startActivity(Intent(this, Main3Activity::class.java))
        }

        button4.setOnClickListener {
            startActivity(Intent(this, Main4Activity::class.java))
        }
    }
}