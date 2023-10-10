package com.daeseong.bottomsheetdialog_test

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main1Activity : AppCompatActivity() {

    private val tag: String = Main1Activity::class.java.simpleName

    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_layout)

        val tv1 = dialog.findViewById<TextView>(R.id.tv1)
        val btn1 = dialog.findViewById<Button>(R.id.btn1)
        val btn2 = dialog.findViewById<Button>(R.id.btn2)

        btn1.setOnClickListener {
            Log.e(tag, tv1.text.toString())
        }

        btn2.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes.windowAnimations = R.style.DialogTheme
            setGravity(Gravity.BOTTOM)
            //decorView.setPadding(0, 0, 0, 100)
        }
    }
}