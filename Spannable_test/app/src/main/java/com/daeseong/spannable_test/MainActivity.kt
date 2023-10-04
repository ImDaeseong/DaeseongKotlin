package com.daeseong.spannable_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val tag = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.button6).setOnClickListener(this)
        findViewById<Button>(R.id.button7).setOnClickListener(this)
        findViewById<Button>(R.id.button8).setOnClickListener(this)
        findViewById<Button>(R.id.button9).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            val intent = when (it.id) {
                R.id.button1 -> Intent(this, Main1Activity::class.java)
                R.id.button2 -> Intent(this, Main2Activity::class.java)
                R.id.button3 -> Intent(this, Main3Activity::class.java)
                R.id.button4 -> Intent(this, Main4Activity::class.java)
                R.id.button5 -> Intent(this, Main5Activity::class.java)
                R.id.button6 -> Intent(this, Main6Activity::class.java)
                R.id.button7 -> Intent(this, Main7Activity::class.java)
                R.id.button8 -> Intent(this, Main8Activity::class.java)
                R.id.button9 -> Intent(this, Main9Activity::class.java)
                else -> null
            }

            intent?.let {
                startActivity(it)
            }
        }
    }

}