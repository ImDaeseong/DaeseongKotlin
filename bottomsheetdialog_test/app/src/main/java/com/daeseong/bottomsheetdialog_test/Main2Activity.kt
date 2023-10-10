package com.daeseong.bottomsheetdialog_test

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {

    private val tag: String = Main2Activity::class.java.simpleName

    private lateinit var dialog: Dialog

    private lateinit var button1: Button
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initDefaultDialog()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            showDefaultDialog()
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            showCustomDialog()
        }
    }

    private fun initDefaultDialog() {

        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_layout)
        dialog.setCancelable(false)

        val tv1 = dialog.findViewById<TextView>(R.id.tv1)

        val btn1 = dialog.findViewById<Button>(R.id.btn1)
        btn1.setOnClickListener {
            Log.e(tag, tv1.text.toString())
        }

        val btn2 = dialog.findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showDefaultDialog(){

        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes.windowAnimations = R.style.DialogTheme
            setGravity(Gravity.BOTTOM)
        }
        dialog.show()
    }


    private fun showCustomDialog() {

        val customDialog = CustomDialog(this)
        customDialog.show()
        customDialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes.windowAnimations = R.style.DialogTheme
            setGravity(Gravity.BOTTOM)
            //decorView.setPadding(0, 0, 0, 100)
        }
    }

}