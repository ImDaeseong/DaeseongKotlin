package com.daeseong.flexboxlayout_test

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FlexboxLayout1Activity : AppCompatActivity() {

    private val tag = FlexboxLayout1Activity::class.java.simpleName

    private var t1: TextView? = null
    private var t2:TextView? = null
    private var t3:TextView? = null
    private var t4:TextView? = null
    private var t5:TextView? = null

    private var et1: EditText? = null
    private var et2:EditText? = null
    private var et3:EditText? = null
    private var et4:EditText? = null
    private var et5:EditText? = null

    private var btnOk: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexbox_layout1)

        init()
        initData()
    }

    private fun init() {

        t1 = findViewById<TextView>(R.id.t1)
        t2 = findViewById<TextView>(R.id.t2)
        t3 = findViewById<TextView>(R.id.t3)
        t4 = findViewById<TextView>(R.id.t4)
        t5 = findViewById<TextView>(R.id.t5)

        et1 = findViewById<EditText>(R.id.et1)
        et2 = findViewById<EditText>(R.id.et2)
        et3 = findViewById<EditText>(R.id.et3)
        et4 = findViewById<EditText>(R.id.et4)
        et5 = findViewById<EditText>(R.id.et5)

        btnOk = findViewById<Button>(R.id.btnOk)
        btnOk!!.setOnClickListener {

            t1!!.text = et1!!.text
            t2!!.text = et2!!.text
            t3!!.text = et3!!.text
            t4!!.text = et4!!.text
            t5!!.text = et5!!.text
        }
    }

    private fun initData() {

        t1!!.text = "입력값1"
        et1!!.setText("입력값1")

        t2!!.text = "입력값2"
        et2!!.setText("입력값2")

        t3!!.text = "자동 줄바꿈 입력문자열3"
        et3!!.setText("자동 줄바꿈 입력문자열3")

        t4!!.text = "TextView4"
        et4!!.setText("TextView4")

        t5!!.text = "마지막 라인 Textview5"
        et5!!.setText("마지막 라인 Textview5")
    }

}