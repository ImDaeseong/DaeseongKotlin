package com.daeseong.bottomsheetdialog_test

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button

class CustomDialog1(context: Context) :  Dialog(context) {

    private val tag: String = CustomDialog1::class.java.simpleName

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_layout1)

        //다이얼로그 영역밖 터치, 백키 입력시 dismiss 막음
        setCancelable(false)

        //dialog 밖에 터치했을 때 사라지기
        setCanceledOnTouchOutside(false)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)

        btn1.setOnClickListener {
            showCustomDialog2()
        }

        btn2.setOnClickListener {
            showCustomDialog2()
        }

        btn3.setOnClickListener {
            showCustomDialog2()
        }
    }

    private fun showCustomDialog2() {

        val customDialog2 = CustomDialog2(context)
        customDialog2.show()
        customDialog2.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes.windowAnimations = R.style.DialogTheme
            setGravity(Gravity.BOTTOM)
        }
    }
}