package com.daeseong.bottomsheetdialog_test

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Main6Activity : AppCompatActivity() {

    private val tag: String = Main6Activity::class.java.simpleName

    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener(View.OnClickListener {

            showCustomDialog1()
        })
    }

    private fun showCustomDialog1() {
        val customDialog1 = CustomDialog1(this)
        customDialog1.show()
        customDialog1.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        customDialog1.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog1.window!!.attributes.windowAnimations = R.style.DialogTheme
        customDialog1.window!!.setGravity(Gravity.BOTTOM)

        //customDialog1.window!!.decorView.setPadding(0, 0, 0, 100)
    }
}