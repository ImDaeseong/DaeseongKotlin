package com.daeseong.coordinatorlayout_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

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

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button1 -> startActivity(Intent(this, coordinatorlayout1Activity::class.java))
            R.id.button2 -> startActivity(Intent(this, coordinatorlayout2Activity::class.java))
            R.id.button3 -> startActivity(Intent(this, coordinatorlayout3Activity::class.java))
            R.id.button4 -> startActivity(Intent(this, coordinatorlayout4Activity::class.java))
            R.id.button5 -> startActivity(Intent(this, coordinatorlayout5Activity::class.java))
        }
    }
}