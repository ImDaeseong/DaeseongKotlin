package com.daeseong.countdownlabel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var button1: Button? = null
    private var button2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener {

            val intent = Intent(this, Main1Activity::class.java)
            startActivity(intent)
        }

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener {

            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }
    }
}
