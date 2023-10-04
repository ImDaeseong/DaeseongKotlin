package com.daeseong.timepicker_test

import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Main3Activity : AppCompatActivity() {

    private val tag: String = Main3Activity::class.java.simpleName

    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            callTimePicker()
        }
    }

    private fun callTimePicker() {

        val calendar: Calendar = Calendar.getInstance()
        var nhour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        var nMinute: Int = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this,
            { _, hourOfDay, minute ->

                nhour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) hourOfDay else minute
                nMinute = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) minute else minute

                Log.e(tag, "nhour:$nhour nMinute:$nMinute")
                Log.e(tag, "hourOfDay:$hourOfDay minute:$minute")
            },
            nhour,
            nMinute,
            true
        )

        timePickerDialog.setTitle("날짜 선택")
        timePickerDialog.show()
    }
}
