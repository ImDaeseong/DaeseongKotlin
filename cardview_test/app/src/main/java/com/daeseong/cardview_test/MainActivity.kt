package com.daeseong.cardview_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

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
            startActivity(Intent(this, CardView1Activity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, CardView2Activity::class.java))
        }

        button3.setOnClickListener {
            startActivity(Intent(this, CardView3Activity::class.java))
        }

        button4.setOnClickListener {
            startActivity(Intent(this, CardView4Activity::class.java))
        }
    }
}
