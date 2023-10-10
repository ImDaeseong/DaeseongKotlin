package com.daeseong.volley_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity1::class.java))
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity2::class.java))
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity3::class.java))
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity4::class.java))
        }

        button5 = findViewById(R.id.button5)
        button5.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity5::class.java))
        }

    }
}