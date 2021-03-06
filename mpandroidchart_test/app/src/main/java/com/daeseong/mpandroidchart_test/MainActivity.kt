package com.daeseong.mpandroidchart_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var button1: Button? = null
    private var button2:Button? = null
    private var button3:Button? = null
    private var button4:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener {
            val intent = Intent(this@MainActivity, Main1Activity::class.java)
            startActivity(intent)
        }


        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener {
            val intent = Intent(this@MainActivity, Main2Activity::class.java)
            startActivity(intent)
        }

        button3 = findViewById(R.id.button3)
        button3!!.setOnClickListener {
            val intent = Intent(this@MainActivity, Main3Activity::class.java)
            startActivity(intent)
        }

        button4 = findViewById(R.id.button4)
        button4!!.setOnClickListener {
            val intent = Intent(this@MainActivity, Main4Activity::class.java)
            startActivity(intent)
        }
    }
}
