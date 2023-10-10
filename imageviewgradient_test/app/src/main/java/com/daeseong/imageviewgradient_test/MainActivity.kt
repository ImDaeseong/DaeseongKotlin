package com.daeseong.imageviewgradient_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button

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
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)

        setButtonClickListeners()
    }

    private fun setButtonClickListeners() {
        setClickListener(button1, Main1Activity::class.java)
        setClickListener(button2, Main2Activity::class.java)
        setClickListener(button3, Main3Activity::class.java)
        setClickListener(button4, Main4Activity::class.java)
        setClickListener(button5, Main5Activity::class.java)
        setClickListener(button6, Main6Activity::class.java)
        setClickListener(button7, Main7Activity::class.java)
        setClickListener(button8, Main8Activity::class.java)
        setClickListener(button9, Main9Activity::class.java)
    }

    private fun setClickListener(button: Button, activityClass: Class<*>) {
        button.setOnClickListener {
            val intent = Intent(this, activityClass)
            startActivity(intent)
        }
    }
}
