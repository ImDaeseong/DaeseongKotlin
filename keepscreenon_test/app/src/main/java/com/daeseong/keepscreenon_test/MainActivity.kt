package com.daeseong.keepscreenon_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var btn1: Button
    private lateinit var btn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener(View.OnClickListener {

            Log.e(tag, "화면을 계속 켜두고 비활성화되지 않도록")
            ScreenLockUtil().lock(this)
        })

        btn2 = findViewById(R.id.btn2)
        btn2.setOnClickListener(View.OnClickListener {

            Log.e(tag, "화면 잠금을 해제하여 화면이 기기의 일반적인 동작")
            ScreenLockUtil().unlock(this)
        })
    }
}
