package com.daeseong.horizontalscrollview_flingtest

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
    private lateinit var button5: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)

        button1.setOnClickListener {
            callActivity(HorizontalScrollView1Activity::class.java)
        }

        button2.setOnClickListener {
            callActivity(HorizontalScrollView2Activity::class.java)
        }

        button3.setOnClickListener {
            callActivity(HorizontalScrollView3Activity::class.java)
        }

        button4.setOnClickListener {
            callActivity(HorizontalScrollView4Activity::class.java)
        }

        button5.setOnClickListener {
            callActivity(HorizontalScrollView5Activity::class.java)
        }
    }

    private fun callActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
