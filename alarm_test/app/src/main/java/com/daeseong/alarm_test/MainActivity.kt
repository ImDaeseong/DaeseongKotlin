package com.daeseong.alarm_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null
    private var button5 : Button? = null
    private var button6 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            val intent = Intent(this, Main1Activity::class.java)
            startActivity(intent)
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener {

            val intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener {

            val intent = Intent(this, Main4Activity::class.java)
            startActivity(intent)
        }

        button5 = findViewById<Button>(R.id.button5)
        button5!!.setOnClickListener {

            val intent = Intent(this, Main5Activity::class.java)
            startActivity(intent)
        }

        button6 = findViewById<Button>(R.id.button6)
        button6!!.setOnClickListener {

            val intent = Intent(this, Main6Activity::class.java)
            startActivity(intent)
        }

    }
}
