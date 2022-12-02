package com.daeseong.spannable_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Main6Activity : AppCompatActivity() {

    private val tag = Main6Activity::class.java.simpleName

    private var tv1: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
    }
}