package com.daeseong.bottomsheetdialog_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout

class BottomFragmentDialog : BottomSheetDialogFragment() {

    companion object {
        private val tag = BottomFragmentDialog::class.java.simpleName
    }

    private var tvl1: TextInputLayout? = null
    private var tvl2:TextInputLayout? = null
    private var button1: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.dialog_bottomlayout, container, false)
        tvl1 = view.findViewById<View>(R.id.tvl1) as TextInputLayout
        tvl2 = view.findViewById<View>(R.id.tvl2) as TextInputLayout
        button1 = view.findViewById<View>(R.id.button1) as Button

        button1!!.setOnClickListener {
            var bCehck = false
            if (checkID()) {
                bCehck = true
            }
            if (checkPwd()) {
                bCehck = true
            }
            if (bCehck) {
                dismiss()
            }
        }

        return view
    }

    private fun checkID(): Boolean {
        val sID = tvl1!!.editText!!.text.toString()
        if (sID.isEmpty()) {
            tvl1!!.error = "아이디를 입력하세요"
        } else {
            tvl1!!.error = null
            tvl1!!.isErrorEnabled = false
        }
        return true
    }

    private fun checkPwd(): Boolean {
        val sPwd = tvl2!!.editText!!.text.toString()
        if (sPwd.isEmpty()) {
            tvl2!!.error = "비밀번호를 입력하세요"
        } else {
            tvl2!!.error = null
            tvl2!!.isErrorEnabled = false
        }
        return true
    }
}
