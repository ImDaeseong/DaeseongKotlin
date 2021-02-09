package com.daeseong.webview_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var btn1 : Button? = null
    private var btn2 : Button? = null
    private var btn3 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById<Button>(R.id.btn1)
        btn1!!.setOnClickListener {

            val intent = Intent(this, WebView1Activity::class.java)
            startActivity(intent)
        }

        btn2 = findViewById<Button>(R.id.btn2)
        btn2!!.setOnClickListener {

            val intent = Intent(this, WebView2Activity::class.java)
            startActivity(intent)
        }

        btn3 = findViewById<Button>(R.id.btn3)
        btn3!!.setOnClickListener {

            val intent = Intent(this, WebView3Activity::class.java)
            startActivity(intent)
        }
    }
}
