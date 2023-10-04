package com.daeseong.dialog_test

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class Style4Activity_Dialog : AppCompatActivity() {

    private val tag: String = Style4Activity_Dialog::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //InitTitleBar()

        setContentView(R.layout.activity_style4__dialog)

        setFinishOnTouchOutside(false)  //다른 영역 클릭 방지
    }

    fun btnOK_Click(v: View) {

        Log.e(tag, "btnOK")
        finish()
    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private fun InitTitleBar() {

        try {
            //안드로이드 8.0 오레오 버전에서만 오류 발생
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        } catch (ex: Exception) {
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}
