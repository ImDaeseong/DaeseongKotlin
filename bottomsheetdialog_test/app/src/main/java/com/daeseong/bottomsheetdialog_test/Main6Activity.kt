package com.daeseong.bottomsheetdialog_test

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Main6Activity : AppCompatActivity() {

    private val tag: String = Main6Activity::class.java.simpleName

    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            showCustomDialog1()
        }
    }

    private fun showCustomDialog1() {
        val customDialog1 = CustomDialog1(this)
        customDialog1.show()
        customDialog1.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes.windowAnimations = R.style.DialogTheme
            setGravity(Gravity.BOTTOM)
            //decorView.setPadding(0, 0, 0, 100)
        }
    }
}