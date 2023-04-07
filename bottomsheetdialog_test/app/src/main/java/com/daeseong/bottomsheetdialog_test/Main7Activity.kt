package com.daeseong.bottomsheetdialog_test

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Main7Activity : AppCompatActivity() {

    private val tag: String = Main7Activity::class.java.simpleName

    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener(View.OnClickListener {

            showCustomDialog3()
        })
    }

    private fun showCustomDialog3() {
        val customDialog3 = CustomDialog3(this)
        customDialog3.show()
        customDialog3.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        customDialog3.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog3.window!!.attributes.windowAnimations = R.style.DialogTheme
        customDialog3.window!!.setGravity(Gravity.BOTTOM)

        //customDialog3.window!!.decorView.setPadding(0, 0, 0, 100)
    }
}