package com.daeseong.font_test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowInsetsControllerCompat

class Font2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTitleBar()

        setContentView(R.layout.activity_font2)
    }

    private fun initTitleBar() {

        //상태바 색상 투명색으로
        window.apply {
            statusBarColor = Color.TRANSPARENT
            WindowInsetsControllerCompat(this, this.decorView).isAppearanceLightStatusBars = true
        }
    }
}