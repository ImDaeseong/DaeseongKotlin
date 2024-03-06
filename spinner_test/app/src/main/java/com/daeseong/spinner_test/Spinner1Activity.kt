package com.daeseong.spinner_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class Spinner1Activity : AppCompatActivity() {

    private val tag = Spinner1Activity::class.java.simpleName

    private lateinit var sp1: Spinner
    private lateinit var sp2: Spinner
    private lateinit var sp3: Spinner

    private val items = arrayOf("항목 1", "항목 2", "항목 3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner1)

        sp1 = findViewById(R.id.sp1)
        sp2 = findViewById(R.id.sp2)
        sp3 = findViewById(R.id.sp3)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sp1.adapter = adapter
        sp1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val sItem = parent?.getItemAtPosition(position).toString()
                Log.e(tag, "sp1 선택 항목:$sItem")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        sp2.adapter = adapter
        sp2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val sItem = parent?.getItemAtPosition(position).toString()
                Log.e(tag, "sp2 선택 항목:$sItem")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        sp3.adapter = adapter
        sp3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val sItem = parent?.getItemAtPosition(position).toString()
                Log.e(tag, "sp3 선택 항목:$sItem")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}