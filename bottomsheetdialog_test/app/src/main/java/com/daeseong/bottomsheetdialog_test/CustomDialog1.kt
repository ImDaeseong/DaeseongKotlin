package com.daeseong.bottomsheetdialog_test

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class CustomDialog1(context: Context) :  Dialog(context) {

    private val tag: String = CustomDialog1::class.java.simpleName

    private var btn1: Button? = null
    private var btn2: Button? = null
    private var btn3: Button? = null

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

        btn1!!.setOnClickListener(View.OnClickListener {

            showCustomDialog2()
        })

        btn2!!.setOnClickListener(View.OnClickListener {

            showCustomDialog2()
        })

        btn3!!.setOnClickListener(View.OnClickListener {

            showCustomDialog2()
        })
    }

    private fun showCustomDialog2() {

        val customDialog2 = CustomDialog2(context)
        customDialog2.show()
        customDialog2.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        customDialog2.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog2.window!!.attributes.windowAnimations = R.style.DialogTheme
        customDialog2.window!!.setGravity(Gravity.BOTTOM)
    }
}