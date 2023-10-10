package com.daeseong.flexboxlayout_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener   {

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

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent = when (v.id) {
            R.id.button1 -> Intent(this, FlexboxLayout1Activity::class.java)
            R.id.button2 -> Intent(this, FlexboxLayout2Activity::class.java)
            R.id.button3 -> Intent(this, FlexboxLayout3Activity::class.java)
            R.id.button4 -> Intent(this, FlexboxLayout4Activity::class.java)
            R.id.button5 -> Intent(this, FlexboxLayout5Activity::class.java)
            else -> null
        }

        intent?.let {
            startActivity(it)
        }
    }
}