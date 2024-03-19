package com.daeseong.spinner_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.daeseong.spinner_test.Controls.ComboBox

class Spinner6Activity : AppCompatActivity() {

    private val tag = Spinner6Activity::class.java.simpleName

    private lateinit var cb1: ComboBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner6)

        cb1 = findViewById(R.id.cb1)

        cb1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val selectedItem = cb1.getItemAtPosition(position) as String
                Log.e(tag, selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        getComboData()
    }

    private fun getComboData() {
        cb1.addItem("항목 1")
        cb1.addItem("항목 2")
        cb1.addItem("항목 3")
    }
}

