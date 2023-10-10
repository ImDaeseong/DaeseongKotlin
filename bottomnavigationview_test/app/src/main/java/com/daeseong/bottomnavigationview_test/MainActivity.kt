package com.daeseong.bottomnavigationview_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent = when (v.id) {
            R.id.button1 -> Intent(this, Main1Activity::class.java)
            R.id.button2 -> Intent(this, Main2Activity::class.java)
            R.id.button3 -> Intent(this, Main3Activity::class.java)
            R.id.button4 -> Intent(this, Main4Activity::class.java)
            R.id.button5 -> Intent(this, Main5Activity::class.java)
            R.id.button6 -> Intent(this, Main6Activity::class.java)
            R.id.button7 -> Intent(this, Main7Activity::class.java)
            else -> null
        }

        intent?.let {
            startActivity(it)
        }
    }
}
