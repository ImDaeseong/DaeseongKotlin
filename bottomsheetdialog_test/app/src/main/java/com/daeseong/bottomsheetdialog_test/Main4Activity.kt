package com.daeseong.bottomsheetdialog_test

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Main4Activity : AppCompatActivity() {

    private val tag: String = Main4Activity::class.java.simpleName

    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            val bottomFragment = BottomFragment()
            bottomFragment.show(supportFragmentManager, "")
        }
    }
}