package com.daeseong.checkbox_test

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Check4Activity : AppCompatActivity() {

    private val tag = Check4Activity::class.java.simpleName

    private lateinit var ci1: CheckImage
    private lateinit var btn1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check4)

        ci1 = findViewById(R.id.ci1)
        ci1.setCheck(false)

        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener {
            Log.e(tag, ci1.isChecked().toString())
        }
    }
}