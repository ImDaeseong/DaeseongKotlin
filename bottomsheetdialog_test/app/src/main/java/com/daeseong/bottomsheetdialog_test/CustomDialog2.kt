package com.daeseong.bottomsheetdialog_test

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText

class CustomDialog2(context: Context) :  Dialog(context) {

    private val tag: String = CustomDialog2::class.java.simpleName

    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var btn1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_layout2)

        //다이얼로그 영역밖 터치, 백키 입력시 dismiss 막음
        setCancelable(false)

        //dialog 밖에 터치했을 때 사라지기
        setCanceledOnTouchOutside(false)

        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        btn1 = findViewById(R.id.btn1)

        et1.addTextChangedListener(createTextWatcher())
        et2.addTextChangedListener(createTextWatcher())

        btn1.setOnClickListener {
            val sValue1 = et1.text.toString()
            val sValue2 = et2.text.toString()
            Log.e(tag, "$sValue1  $sValue2")
            dismiss()
        }
    }

    private fun createTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }
        }
    }
}