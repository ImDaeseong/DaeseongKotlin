package com.daeseong.mpandroidchart_test

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
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            val intent = when (it.id) {
                R.id.button1 -> Intent(this, Chart1Activity::class.java)
                R.id.button2 -> Intent(this, Chart2Activity::class.java)
                R.id.button3 -> Intent(this, Chart3Activity::class.java)
                R.id.button4 -> Intent(this, Chart4Activity::class.java)
                R.id.button5 -> Intent(this, Chart5Activity::class.java)
                R.id.button6 -> Intent(this, Chart6Activity::class.java)
                R.id.button7 -> Intent(this, Chart7Activity::class.java)
                R.id.button8 -> Intent(this, Chart8Activity::class.java)
                R.id.button9 -> Intent(this, Chart9Activity::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
        }
    }
}