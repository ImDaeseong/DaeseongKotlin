package com.daeseong.gridlayout_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            val intent = Intent(this, GridLayout1Activity::class.java)
            startActivity(intent)
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            val intent = Intent(this, GridLayout2Activity::class.java)
            startActivity(intent)
        }
    }
}
