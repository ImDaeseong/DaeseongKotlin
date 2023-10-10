package com.daeseong.bottomsheetdialog_test

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class CustomDialog(context: Context) :  Dialog(context) {

    private val tag: String = CustomDialog::class.java.simpleName

    private lateinit var tv1: TextView
    private lateinit var btn1: Button
    private lateinit var btn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_layout)

        //다이얼로그 영역밖 터치, 백키 입력시 dismiss 막음
        setCancelable(false)

        //dialog 밖에 터치했을 때 사라지기
        setCanceledOnTouchOutside(false)

        tv1 = findViewById(R.id.tv1)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)

        btn1.setOnClickListener {
            Log.e(tag, tv1.text.toString())
        }

        btn2.setOnClickListener {
            dismiss()
        }
    }
}