package com.daeseong.dialog_test

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window

class Style9Activity_Dialog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_style9_dialog)

        setFinishOnTouchOutside(false)  //다른 영역 클릭 방지

        changeDisplay()
    }

    private fun changeDisplay() {

        try {
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            val screenLength = size.y
            val screenwidth = size.x

            if (screenLength > 0 && screenwidth > 0) {
                val viewWidth = screenwidth - Utils.dip2px(this, 48F)
                window.setLayout(viewWidth, ViewGroup.LayoutParams.WRAP_CONTENT)
                val wmlp = window.attributes
                wmlp.gravity = Gravity.CENTER
                wmlp.x = 0
                wmlp.y = 0
                window.attributes = wmlp
            }
        } catch (e: Exception) {
        }
    }
}