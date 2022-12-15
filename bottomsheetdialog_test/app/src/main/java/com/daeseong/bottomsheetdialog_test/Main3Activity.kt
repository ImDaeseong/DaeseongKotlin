package com.daeseong.bottomsheetdialog_test

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog

class Main3Activity : AppCompatActivity() {

    private val tag: String = Main3Activity::class.java.simpleName

    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener { showBottomSheetDialog(this) }
    }

    private fun showBottomSheetDialog(context: Context) {

        val bottomSheetDialog = BottomSheetDialog(this, R.style.BottonSheetDialogTheme)

        val view = LayoutInflater.from(applicationContext).inflate(R.layout.dialog_layout, findViewById<ConstraintLayout>(R.id.dialogID))

        val tv1 = view.findViewById<TextView>(R.id.tv1)

        view.findViewById<View>(R.id.btn1).setOnClickListener {

            Log.e(tag, tv1.text.toString() )
        }

        view.findViewById<View>(R.id.btn2).setOnClickListener {

            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.setCancelable(false)
        bottomSheetDialog.setCanceledOnTouchOutside(false)
        bottomSheetDialog.show()
    }
}
