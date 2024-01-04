package com.daeseong.paging3_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)

        button1.setOnClickListener {
            startActivity(Intent(this, Main1Activity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }

        button3.setOnClickListener {
            startActivity(Intent(this, Main3Activity::class.java))
        }

        button4.setOnClickListener {
            startActivity(Intent(this, Main4Activity::class.java))
        }

        button5.setOnClickListener {
            startActivity(Intent(this, Main5Activity::class.java))
        }

        button6.setOnClickListener {
            startActivity(Intent(this, Main6Activity::class.java))
        }
    }
}