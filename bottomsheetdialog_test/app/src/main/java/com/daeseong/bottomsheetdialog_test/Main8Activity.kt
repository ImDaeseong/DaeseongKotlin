package com.daeseong.bottomsheetdialog_test

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Main8Activity : AppCompatActivity() {

    private val tag = Main8Activity::class.java.simpleName

    private lateinit var cL1: View
    private lateinit var btn1_1: Button
    private lateinit var btn2_1: Button
    private lateinit var btn3_1: Button

    private lateinit var cL2: View
    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var btn1: Button

    private var bClick = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        init()

        cL1 = findViewById(R.id.cL1)
        cL2 = findViewById(R.id.cL2)

        setVisibleView()

        btn1_1 = findViewById(R.id.btn1_1)
        btn2_1 = findViewById(R.id.btn2_1)
        btn3_1 = findViewById(R.id.btn3_1)

        setButtonClickListeners()

        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)

        setEditTextListeners()

        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {

        //Log.e(tag, "onBackPressed")

        changeView()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_DOWN && isOutOfBounds(this, event)) {

            //Log.e(tag, "다이얼로그 영역밖 터치")

            changeView()
        }

        return true
    }

    private fun setVisibleView() {

        if (bClick) {
            cL1.visibility = View.GONE
            cL2.visibility = View.VISIBLE
        } else {
            cL1.visibility = View.VISIBLE
            cL2.visibility = View.GONE
        }
    }

    private fun init() {

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.attributes.windowAnimations = R.style.DialogTheme
        window.setGravity(Gravity.BOTTOM)

        //window.decorView.setPadding(0, 0, 0, 100)
    }

    private fun changeView() {

        if (bClick) {
            bClick = false
            setVisibleView()
        } else {
            finish()
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

    private fun setButtonClickListeners() {
        btn1_1.setOnClickListener {
            bClick = !bClick
            setVisibleView()
        }

        btn2_1.setOnClickListener {
            bClick = !bClick
            setVisibleView()
        }

        btn3_1.setOnClickListener {
            bClick = !bClick
            setVisibleView()
        }
    }

    private fun setEditTextListeners() {
        et1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        et2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}