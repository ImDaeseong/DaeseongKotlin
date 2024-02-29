package com.daeseong.font_test

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTitleBar()

        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            val intent = when (it.id) {
                R.id.button1 -> Intent(this, Font1Activity::class.java)
                R.id.button2 -> Intent(this, Font2Activity::class.java)
                R.id.button3 -> Intent(this, Font3Activity::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
        }
    }

    private fun initTitleBar() {

        //상태바 색상 회색으로
        window.apply {
            statusBarColor = Color.GRAY
            WindowInsetsControllerCompat(this, this.decorView).isAppearanceLightStatusBars = true
        }
    }
}