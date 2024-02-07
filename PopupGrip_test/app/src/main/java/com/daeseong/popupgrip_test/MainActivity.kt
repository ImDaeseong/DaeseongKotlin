package com.daeseong.popupgrip_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button1 -> {
                val intent1 = Intent(this, Popup1Activity::class.java)
                startActivity(intent1)
                overridePendingTransition(R.anim.slide_in_bottom, 0)
            }
            R.id.button2 -> {
                val intent2 = Intent(this, Popup2Activity::class.java)
                startActivity(intent2)
                overridePendingTransition(R.anim.slide_in_bottom, 0)
            }
            R.id.button3 -> {
                val intent3 = Intent(this, Popup3Activity::class.java)
                startActivity(intent3)
                overridePendingTransition(R.anim.slide_in_bottom, 0)
            }
        }
    }
}