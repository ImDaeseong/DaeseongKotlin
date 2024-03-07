package com.daeseong.gridlayoutmanager_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener  {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            val intent = when (it.id) {
                R.id.button1 -> Intent(this, Gridlayout1Activity::class.java)
                R.id.button2 -> Intent(this, Gridlayout2Activity::class.java)
                R.id.button3 -> Intent(this, Gridlayout3Activity::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
        }
    }
}