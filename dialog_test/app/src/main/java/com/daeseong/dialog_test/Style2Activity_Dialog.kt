package com.daeseong.dialog_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

class Style2Activity_Dialog : AppCompatActivity() {

    private val tag: String = Style2Activity_Dialog::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_style2__dialog)

        setFinishOnTouchOutside(false)  //다른 영역 클릭 방지
    }

    fun btnOK_Click(v: View) {

        Log.e(tag, "btnOK")
        finish()
    }
}
