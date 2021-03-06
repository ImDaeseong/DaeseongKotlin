package com.daeseong.timepicker_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val tag: String = MainActivity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.button1)
        button2 = findViewById<Button>(R.id.button2)
        button3 = findViewById<Button>(R.id.button3)
        button4 = findViewById<Button>(R.id.button4)

        button1!!.setOnClickListener(this)
        button2!!.setOnClickListener(this)
        button3!!.setOnClickListener(this)
        button4!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {

            when(v.id){
                R.id.button1 -> {

                    val intent = Intent(this, Main1Activity::class.java)
                    startActivity(intent)
                }

                R.id.button2 -> {

                    val intent = Intent(this, Main2Activity::class.java)
                    startActivity(intent)
                }

                R.id.button3 -> {

                    val intent = Intent(this, Main3Activity::class.java)
                    startActivity(intent)
                }

                R.id.button4 -> {

                    val intent = Intent(this, Main4Activity::class.java)
                    startActivity(intent)
                }

            }

        }
    }
}
