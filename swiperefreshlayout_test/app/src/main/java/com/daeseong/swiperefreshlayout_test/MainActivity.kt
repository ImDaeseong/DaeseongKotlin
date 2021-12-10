package com.daeseong.swiperefreshlayout_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {
            startActivity(Intent(this, MainActivity1::class.java))
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener {
            startActivity(Intent(this, MainActivity4::class.java))
        }
    }
}