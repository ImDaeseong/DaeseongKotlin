package com.daeseong.dialog_test

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

class Style7Activity_Dialog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_style7_dialog)

        setFinishOnTouchOutside(false)  //다른 영역 클릭 방지

        changeDisplay()
    }

    private fun changeDisplay() {

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.attributes.windowAnimations = R.style.BottomPopupSlideTheme
        window.setGravity(Gravity.BOTTOM)
    }
}