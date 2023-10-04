package com.daeseong.dialog_test

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button

class Custom1Dialog(context: Context, private val closeListener: View.OnClickListener) : Dialog(context) {

    private val tag: String = Custom1Dialog::class.java.simpleName

    private var btnCancelDialog: Button? = null
    private var btnCloseDialog: Button? = null

    init {
        setCancelable(false)
        setCanceledOnTouchOutside(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom1_dialog)

        btnCancelDialog = findViewById(R.id.btncancel_dialog)
        btnCancelDialog?.setOnClickListener {
            dismiss()
        }

        btnCloseDialog = findViewById(R.id.btnclose_dialog)
        btnCloseDialog?.setOnClickListener {
            dismiss()
        }
    }
}
