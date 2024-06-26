package com.daeseong.webview_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

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
        button1.setOnClickListener {
            startActivity(Intent(this, WebView1Activity::class.java))
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            startActivity(Intent(this, WebView2Activity::class.java))
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            startActivity(Intent(this, WebView3Activity::class.java))
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {
            startActivity(Intent(this, WebView4Activity::class.java))
        }
    }
}
