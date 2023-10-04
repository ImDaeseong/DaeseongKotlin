package com.daeseong.timepicker_test

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Main4Activity : AppCompatActivity() {

    private val tag: String = Main4Activity::class.java.simpleName

    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            callTimePicker()
        }
    }

    private fun callTimePicker() {

        val timePickerDialogEx = TimePickerDialogEx(this)

        timePickerDialogEx.setOnDismissListener {
            when (timePickerDialogEx.BUTTON_TYPE) {
                Dialog.BUTTON_POSITIVE -> {
                    Log.e(tag, "nhour:${timePickerDialogEx.getHour()} nMinute:${timePickerDialogEx.getMinute()}")
                }
                Dialog.BUTTON_NEGATIVE -> {
                    Log.e(tag, "BUTTON_NEGATIVE")
                }
            }
        }

        timePickerDialogEx.show()
    }
}
