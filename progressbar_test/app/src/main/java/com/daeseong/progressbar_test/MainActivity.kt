package com.daeseong.progressbar_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() , View.OnClickListener {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.btn1)
        button2 = findViewById(R.id.btn2)
        button3 = findViewById(R.id.btn3)
        button4 = findViewById(R.id.btn4)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent: Intent = when (v.id) {
            R.id.btn1 -> Intent(this, Main2Activity::class.java)
            R.id.btn2 -> Intent(this, Main3Activity::class.java)
            R.id.btn3 -> Intent(this, Main4Activity::class.java)
            R.id.btn4 -> Intent(this, Main5Activity::class.java)
            else -> throw IllegalArgumentException("Unexpected button click")
        }

        startActivity(intent)
    }
}
