package com.daeseong.bottomsheetdialog_test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomFragment : BottomSheetDialogFragment() {

    companion object {
        private val tag = BottomFragment::class.java.simpleName
    }

    private var tv1: TextView? = null
    private var btn1: Button? = null
    private var btn2: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.dialog_layout, container, false)
        tv1 = view.findViewById(R.id.tv1)
        btn1 = view.findViewById(R.id.btn1)
        btn2 = view.findViewById(R.id.btn2)

        btn1!!.setOnClickListener(View.OnClickListener {

            Log.e(tag, tv1!!.text.toString())
        })

        btn2!!.setOnClickListener(View.OnClickListener {

            dismiss()
        })

        return view
    }
}
