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

    private lateinit var tvl1: TextInputLayout
    private lateinit var tvl2: TextInputLayout
    private lateinit var button1: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.dialog_bottomlayout, container, false)

        tvl1 = view.findViewById(R.id.tvl1)
        tvl2 = view.findViewById(R.id.tvl2)
        button1 = view.findViewById(R.id.button1)

        button1.setOnClickListener {
            val isIdValid = checkID()
            val isPwdValid = checkPwd()

            if (isIdValid && isPwdValid) {
                dismiss()
            }
        }

        return view
    }

    private fun checkID(): Boolean {
        val sID = tvl1.editText?.text.toString()
        return if (sID.isNullOrEmpty()) {
            tvl1.error = "아이디를 입력하세요"
            false
        } else {
            tvl1.error = null
            tvl1.isErrorEnabled = false
            true
        }
    }

    private fun checkPwd(): Boolean {
        val sPwd = tvl2.editText?.text.toString()
        return if (sPwd.isNullOrEmpty()) {
            tvl2.error = "비밀번호를 입력하세요"
            false
        } else {
            tvl2.error = null
            tvl2.isErrorEnabled = false
            true
        }
    }
}
