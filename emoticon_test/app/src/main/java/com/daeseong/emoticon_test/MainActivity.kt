package com.daeseong.emoticon_test

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var et1: EditText
    private lateinit var btn1: Button
    private lateinit var tv1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et1 = findViewById(R.id.et1)
        btn1 = findViewById(R.id.btn1)
        tv1 = findViewById(R.id.tv1)

        btn1.setOnClickListener {
            val inputText = et1.text.toString()
            tv1.text = inputText
        }

        init()
    }

    private fun init() {
        val value1 = "❤️💚♤☆♧¥♡♤☆♧🐵🐒🐕🦁🐕‍🐺🦎🦄🐴🐆🐅🐍🦌"
        val value2 = "♤☆♧¥♡♤☆♧"
        et1.setText(value1)
    }
}