package com.daeseong.dialog_test


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button


class Custom1_Dialog(context: Context, closeListener: View.OnClickListener) :  Dialog(context) {

    private val tag: String = Custom1_Dialog::class.java.simpleName

    private var mcloseListener: View.OnClickListener? = null

    private var btncancel_dialog: Button? = null
    private var btnclose_dialog: Button? = null

    init {
        mcloseListener = closeListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.custom1_dialog)
        setCancelable(false)
        setCanceledOnTouchOutside(true) // dialog 밖에 터치했을 때 사라지기

        btncancel_dialog = findViewById<Button>(R.id.btncancel_dialog)
        btncancel_dialog!!.setOnClickListener {

            dismiss()
        }

        btnclose_dialog = findViewById<Button>(R.id.btnclose_dialog)
        btnclose_dialog!!.setOnClickListener {

            dismiss()
        }
    }
}