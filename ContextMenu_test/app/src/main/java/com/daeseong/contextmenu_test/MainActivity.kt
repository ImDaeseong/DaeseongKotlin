package com.daeseong.contextmenu_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button1 -> startActivity(Intent(this, Contextmenu1Activity::class.java))
            R.id.button2 -> startActivity(Intent(this, Popupmenu1Activity::class.java))
            R.id.button3 -> startActivity(Intent(this, Popupmenu2Activity::class.java))
            R.id.button4 -> startActivity(Intent(this, Popupmenu3Activity::class.java))
        }
    }
}