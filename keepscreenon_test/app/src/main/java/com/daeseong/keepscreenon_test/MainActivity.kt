package com.daeseong.keepscreenon_test

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var btn1: Button? = null
    private var btn2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById<Button>(R.id.btn1)
        btn1!!.setOnClickListener(View.OnClickListener {
            ScreenLockutil().Lock(this)
        })

        btn2 = findViewById<Button>(R.id.btn2)
        btn2!!.setOnClickListener(View.OnClickListener {
            ScreenLockutil().Unlock(this)
        })
    }
}
