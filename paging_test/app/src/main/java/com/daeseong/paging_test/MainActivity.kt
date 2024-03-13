package com.daeseong.paging_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

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
    }

    override fun onClick(v: View?) {
        val intentClass = when (v?.id) {
            R.id.button1 -> Main1Activity::class.java
            R.id.button2 -> Main2Activity::class.java
            R.id.button3 -> Main3Activity::class.java
            R.id.button4 -> Main4Activity::class.java
            R.id.button5 -> Main5Activity::class.java
            R.id.button6 -> Main6Activity::class.java
            R.id.button7 -> Main7Activity::class.java
            R.id.button8 -> Main8Activity::class.java
            else -> null
        }

        intentClass?.let {
            val intent = Intent(this, it)
            startActivity(intent)
        }
    }
}