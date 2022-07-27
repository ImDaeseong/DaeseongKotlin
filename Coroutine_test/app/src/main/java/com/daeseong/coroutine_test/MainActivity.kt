package com.daeseong.coroutine_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() , View.OnClickListener {

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener(this)

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener(this)

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener(this)

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (v != null) {

            when (v.id) {

                R.id.button1 -> {

                    val intent = Intent(this, coroutine1Activity::class.java)
                    startActivity(intent)
                }

                R.id.button2 -> {

                    val intent = Intent(this, coroutine2Activity::class.java)
                    startActivity(intent)
                }

                R.id.button3 -> {

                    val intent = Intent(this, coroutine3Activity::class.java)
                    startActivity(intent)
                }

                R.id.button4 -> {

                    val intent = Intent(this, coroutine4Activity::class.java)
                    startActivity(intent)
                }
            }
        }

    }
}