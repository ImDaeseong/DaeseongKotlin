package com.daeseong.volley_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    private var button5: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity1::class.java)
            startActivity(intent)
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity3::class.java)
            startActivity(intent)
        }

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity4::class.java)
            startActivity(intent)
        }

        button5 = findViewById<Button>(R.id.button5)
        button5!!.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity5::class.java)
            startActivity(intent)
        }
    }
}