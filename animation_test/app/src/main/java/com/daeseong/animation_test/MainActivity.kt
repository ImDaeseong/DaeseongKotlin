package com.daeseong.animation_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null
    private var button5 : Button? = null
    private var button6 : Button? = null
    private var button7 : Button? = null
    private var button8 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            val intent = Intent(this, Animate1Activity::class.java)
            startActivity(intent)
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            val intent = Intent(this, Animate2Activity::class.java)
            startActivity(intent)
        }

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener {

            val intent = Intent(this, Animate3Activity::class.java)
            startActivity(intent)
        }

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener {

            val intent = Intent(this, Animate4Activity::class.java)
            startActivity(intent)
        }

        button5 = findViewById<Button>(R.id.button5)
        button5!!.setOnClickListener {

            val intent = Intent(this, Animate5Activity::class.java)
            startActivity(intent)
        }

        button6 = findViewById<Button>(R.id.button6)
        button6!!.setOnClickListener {

            val intent = Intent(this, Animate6Activity::class.java)
            startActivity(intent)
        }

        button7 = findViewById<Button>(R.id.button7)
        button7!!.setOnClickListener {

            val intent = Intent(this, Animate7Activity::class.java)
            startActivity(intent)
        }

        button8 = findViewById<Button>(R.id.button8)
        button8!!.setOnClickListener {

            val intent = Intent(this, Animate8Activity::class.java)
            startActivity(intent)
        }
    }
}
