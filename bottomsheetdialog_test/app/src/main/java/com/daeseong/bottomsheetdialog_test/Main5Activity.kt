package com.daeseong.bottomsheetdialog_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Main5Activity : AppCompatActivity() {

    private val tag: String = Main5Activity::class.java.simpleName

    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            val bottomFragmentDialog = BottomFragmentDialog()
            bottomFragmentDialog.show(supportFragmentManager, "")
        }
    }
}