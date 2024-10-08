package com.daeseong.timepicker_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val tag: String = MainActivity::class.java.simpleName

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

        setOnClickListeners(button1, button2, button3, button4)
    }

    private fun setOnClickListeners(vararg buttons: Button) {
        buttons.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View) {
        val intent = when (v.id) {
            R.id.button1 -> Intent(this, Main1Activity::class.java)
            R.id.button2 -> Intent(this, Main2Activity::class.java)
            R.id.button3 -> Intent(this, Main3Activity::class.java)
            R.id.button4 -> Intent(this, Main4Activity::class.java)
            else -> throw IllegalArgumentException("Unexpected view ID")
        }
        startActivity(intent)
    }
}
