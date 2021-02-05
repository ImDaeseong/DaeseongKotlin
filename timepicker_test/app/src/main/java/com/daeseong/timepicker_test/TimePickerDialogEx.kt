package com.daeseong.timepicker_test

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TimePicker


class TimePickerDialogEx(context: Context) : Dialog(context) {

    private val tag: String = TimePickerDialogEx::class.java.simpleName;

    var BUTTON_TYPE = BUTTON_NEGATIVE

    private var button1: Button? = null
    private var button2: Button? = null
    private var timepicker1: TimePicker? = null
    private var nhour = 0
    private var nMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.timepicker_dialog)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            BUTTON_TYPE = Dialog.BUTTON_NEGATIVE
            dismiss()
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            BUTTON_TYPE = Dialog.BUTTON_POSITIVE
            dismiss()
        }

        timepicker1 = findViewById<TimePicker>(R.id.timepicker1)
        timepicker1!!.setIs24HourView(true)
        timepicker1!!.setOnTimeChangedListener { view, hourOfDay, minute ->

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                nhour = timepicker1!!.hour
                nMinute = timepicker1!!.minute
            } else {
                nhour = timepicker1!!.currentHour
                nMinute = timepicker1!!.currentMinute
            }

            Log.e(tag, "nhour:$nhour nMinute:$nMinute")
            Log.e(tag, "hourOfDay:$hourOfDay minute:$minute")
        }
    }

    fun getHour(): String {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            nhour = timepicker1!!.hour
            nMinute = timepicker1!!.minute
        } else {
            nhour = timepicker1!!.currentHour
            nMinute = timepicker1!!.currentMinute
        }

        return nhour.toString()
    }

    fun getMinute(): String? {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            nhour = timepicker1!!.hour
            nMinute = timepicker1!!.minute
        } else {
            nhour = timepicker1!!.currentHour
            nMinute = timepicker1!!.currentMinute
        }

        return nMinute.toString()
    }
}
