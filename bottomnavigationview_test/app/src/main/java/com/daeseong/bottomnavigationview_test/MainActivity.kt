package com.daeseong.bottomnavigationview_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

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

        button1 = findViewById<View>(R.id.button1) as Button
        button2 = findViewById<View>(R.id.button2) as Button
        button3 = findViewById<View>(R.id.button3) as Button
        button4 = findViewById<View>(R.id.button4) as Button
        button5 = findViewById<View>(R.id.button5) as Button
        button6 = findViewById<View>(R.id.button6) as Button
        button7 = findViewById<View>(R.id.button7) as Button

        button1!!.setOnClickListener(this)
        button2!!.setOnClickListener(this)
        button3!!.setOnClickListener(this)
        button4!!.setOnClickListener(this)
        button5!!.setOnClickListener(this)
        button6!!.setOnClickListener(this)
        button7!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button1 -> startActivity(Intent(this, Main1Activity::class.java))
            R.id.button2 -> startActivity(Intent(this, Main2Activity::class.java))
            R.id.button3 -> startActivity(Intent(this, Main3Activity::class.java))
            R.id.button4 -> startActivity(Intent(this, Main4Activity::class.java))
            R.id.button5 -> startActivity(Intent(this, Main5Activity::class.java))
            R.id.button6 -> startActivity(Intent(this, Main6Activity::class.java))
            R.id.button7 -> startActivity(Intent(this, Main7Activity::class.java))
        }
    }
}
