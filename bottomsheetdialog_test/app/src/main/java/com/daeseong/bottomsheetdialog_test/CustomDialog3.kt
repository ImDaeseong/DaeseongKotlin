package com.daeseong.bottomsheetdialog_test

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.Button
import android.widget.EditText

class CustomDialog3(context: Context) :  Dialog(context) {

    private val tag: String = CustomDialog3::class.java.simpleName

    private lateinit var cL1: View
    private lateinit var btn1_1: Button
    private lateinit var btn2_1: Button
    private lateinit var btn3_1: Button

    private lateinit var cL2: View
    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var btn1: Button

    private var bClick = false

    private fun setVisibleView() {

        if (bClick) {
            cL1.visibility = View.GONE
            cL2.visibility = View.VISIBLE
        } else {
            cL1.visibility = View.VISIBLE
            cL2.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_layout3)

        cL1 = findViewById(R.id.cL1)
        cL2 = findViewById(R.id.cL2)

        setVisibleView()

        btn1_1 = findViewById(R.id.btn1_1)
        btn2_1 = findViewById(R.id.btn2_1)
        btn3_1 = findViewById(R.id.btn3_1)

        setButtonClickListeners()

        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)

        et1.addTextChangedListener(createTextWatcher())
        et2.addTextChangedListener(createTextWatcher())

        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener {
            dismiss()
        }
    }

    override fun onBackPressed() {

        Log.e(tag, "onBackPressed")

        changeView()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (event.action == MotionEvent.ACTION_DOWN && isOutOfBounds(context, event)){

            changeView()
        }

        return true
    }

    private fun setButtonClickListeners() {
        val clickListener: View.OnClickListener = View.OnClickListener {
            bClick = !bClick
            setVisibleView()
        }

        btn1_1.setOnClickListener(clickListener)
        btn2_1.setOnClickListener(clickListener)
        btn3_1.setOnClickListener(clickListener)
    }

    private fun changeView() {
        if (bClick) {
            bClick = false
            setVisibleView()
        } else {
            dismiss()
        }
    }

    private fun isOutOfBounds(context: Context, event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        val slop = ViewConfiguration.get(context).scaledWindowTouchSlop
        val decorView = window?.decorView
        return (x < -slop || y < -slop
                || x > decorView?.width!! + slop
                || y > decorView.height + slop)
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