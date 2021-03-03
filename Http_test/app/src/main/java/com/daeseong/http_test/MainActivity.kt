package com.daeseong.http_test

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
    private var button5 : Button? = null
    private var button6 : Button? = null
    private var button7 : Button? = null

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

        button5 = findViewById<Button>(R.id.button5)
        button5!!.setOnClickListener(this)

        button6 = findViewById<Button>(R.id.button6)
        button6!!.setOnClickListener(this)

        button7 = findViewById<Button>(R.id.button7)
        button7!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (v != null) {

            when (v.id) {

                R.id.button1 -> {

                    val intent = Intent(this, ImageView1Activity::class.java)
                    startActivity(intent)
                }

                R.id.button2 -> {

                    val intent = Intent(this, ImageView2Activity::class.java)
                    startActivity(intent)
                }

                R.id.button3 -> {

                    val intent = Intent(this, TextView1Activity::class.java)
                    startActivity(intent)
                }

                R.id.button4 -> {

                    val intent = Intent(this, SendText1Activity::class.java)
                    startActivity(intent)
                }

                R.id.button5 -> {

                    val intent = Intent(this, ImageTextView2Activity::class.java)
                    startActivity(intent)
                }

                R.id.button6 -> {

                    val intent = Intent(this, TextView2Activity::class.java)
                    startActivity(intent)
                }

                R.id.button7 -> {

                    val intent = Intent(this, ImageTextView1Activity::class.java)
                    startActivity(intent)
                }
            }
        }

    }
}
