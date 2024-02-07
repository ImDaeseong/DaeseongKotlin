package com.daeseong.cardview_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button

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

        button1.setOnClickListener {
            startActivity(Intent(this, CardView1Activity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, CardView2Activity::class.java))
        }

        button3.setOnClickListener {
            startActivity(Intent(this, CardView3Activity::class.java))
        }

        button4.setOnClickListener {
            startActivity(Intent(this, CardView4Activity::class.java))
        }

        button5.setOnClickListener {
            startActivity(Intent(this, CardView5Activity::class.java))
        }

        button6.setOnClickListener {
            startActivity(Intent(this, CardView6Activity::class.java))
        }

        button7.setOnClickListener {
            startActivity(Intent(this, CardView7Activity::class.java))
        }
    }
}
