package com.daeseong.timepicker_test

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Main4Activity : AppCompatActivity() {

    private val tag: String = Main4Activity::class.java.simpleName;

    private var button1 : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            callTimePicker()
        })
    }

    private fun callTimePicker() {

        val timePickerDialogEx = TimePickerDialogEx(this)
        timePickerDialogEx.show()
        timePickerDialogEx.setOnDismissListener {

            if (timePickerDialogEx.BUTTON_TYPE == Dialog.BUTTON_POSITIVE) {

                Log.e(tag,"nhour:" + timePickerDialogEx.getHour() + " nMinute:" + timePickerDialogEx.getMinute())

            } else if (timePickerDialogEx.BUTTON_TYPE == Dialog.BUTTON_NEGATIVE) {

                Log.e(tag, "BUTTON_NEGATIVE")
            }
        }
    }
}
