package com.daeseong.horizontalscrollview_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener {

            val intent = Intent(this, HorScroll1Activity::class.java)
            startActivity(intent)
        }

        button2 = findViewById<View>(R.id.button2) as Button
        button2!!.setOnClickListener {

            val intent = Intent(this, HorScroll2Activity::class.java)
            startActivity(intent)
        }

        button3 = findViewById<View>(R.id.button3) as Button
        button3!!.setOnClickListener {

            val intent = Intent(this, HorScroll3Activity::class.java)
            startActivity(intent)
        }

        button4 = findViewById<View>(R.id.button4) as Button
        button4!!.setOnClickListener {

            val intent = Intent(this, HorScroll4Activity::class.java)
            startActivity(intent)
        }
    }
}
