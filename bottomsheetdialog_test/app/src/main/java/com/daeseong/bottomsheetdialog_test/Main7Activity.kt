package com.daeseong.bottomsheetdialog_test

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Main7Activity : AppCompatActivity() {

    private val tag: String = Main7Activity::class.java.simpleName

    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            showCustomDialog3()
        }
    }

    private fun showCustomDialog3() {
        val customDialog3 = CustomDialog3(this)
        customDialog3.show()
        customDialog3.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes.windowAnimations = R.style.DialogTheme
            setGravity(Gravity.BOTTOM)
            //decorView.setPadding(0, 0, 0, 100)
        }
    }
}