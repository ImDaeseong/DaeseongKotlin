package com.daeseong.bottomsheetdialog_test

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {

    private val tag: String = Main2Activity::class.java.simpleName

    private lateinit var dialog : Dialog

    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initDefaultDialog()

        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener(View.OnClickListener {

            showDefaultDialog()
        })
    }

    private fun initDefaultDialog() {

        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_layout)
        dialog.setCancelable(false)

        val tv1 = dialog.findViewById<TextView>(R.id.tv1)

        val btn1 = dialog.findViewById<Button>(R.id.btn1)
        btn1.setOnClickListener {

            Log.e(tag, tv1!!.text.toString())
        }

        val btn2 = dialog.findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener {

            dialog.dismiss()
        }
    }

    private fun showDefaultDialog(){

        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogTheme
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.show()
    }
}