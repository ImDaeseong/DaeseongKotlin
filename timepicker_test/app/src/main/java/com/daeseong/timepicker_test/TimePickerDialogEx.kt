package com.daeseong.timepicker_test

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TimePicker

class TimePickerDialogEx(context: Context) : Dialog(context) {

    private val tag: String = TimePickerDialogEx::class.java.simpleName

    var BUTTON_TYPE = BUTTON_NEGATIVE

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var timepicker1: TimePicker
    private var nhour: Int = 0
    private var nMinute: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.timepicker_dialog)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            BUTTON_TYPE = Dialog.BUTTON_NEGATIVE
            dismiss()
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            BUTTON_TYPE = Dialog.BUTTON_POSITIVE
            dismiss()
        }

        timepicker1 = findViewById(R.id.timepicker1)
        timepicker1.setOnTimeChangedListener { _, hourOfDay, minute ->

            nhour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) hourOfDay else minute
            nMinute = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) minute else minute

            Log.e(tag, "nhour:$nhour nMinute:$nMinute")
            Log.e(tag, "hourOfDay:$hourOfDay minute:$minute")
        }
    }

    fun getHour(): String {
        return nhour.toString()
    }

    fun getMinute(): String {
        return nMinute.toString()
    }
}
