package com.daeseong.threadtask_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    private var button5: Button? = null
    private var button6: Button? = null
    private var button7: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Main1Activity::class.java)
            startActivity(intent)
        })

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        })

        button3 = findViewById(R.id.button3)
        button3!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        })

        button4 = findViewById(R.id.button4)
        button4!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Main4Activity::class.java)
            startActivity(intent)
        })

        button5 = findViewById(R.id.button5)
        button5!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Main5Activity::class.java)
            startActivity(intent)
        })

        button6 = findViewById(R.id.button6)
        button6!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Main6Activity::class.java)
            startActivity(intent)
        })

        button7 = findViewById(R.id.button7)
        button7!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Main7Activity::class.java)
            startActivity(intent)
        })
    }
}