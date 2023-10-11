package com.daeseong.bluetooth_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = MainActivity::class.java.simpleName

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
        button1.setOnClickListener(this)

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener(this)

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener(this)

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener(this)

        button5 = findViewById(R.id.button5)
        button5.setOnClickListener(this)

        button6 = findViewById(R.id.button6)
        button6.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button1 -> startActivity(Intent(this, MainActivity1::class.java))
            R.id.button2 -> startActivity(Intent(this, MainActivity2::class.java))
            R.id.button3 -> startActivity(Intent(this, MainActivity3::class.java))
            R.id.button4 -> startActivity(Intent(this, MainActivity4::class.java))
            R.id.button5 -> startActivity(Intent(this, MainActivity5::class.java))
            R.id.button6 -> startActivity(Intent(this, MainActivity6::class.java))
        }
    }
}