package com.daeseong.spinner_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class Spinner2Activity : AppCompatActivity() {

    private val tag = Spinner2Activity::class.java.simpleName

    private lateinit var sp1: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner2)

        sp1 = findViewById(R.id.sp1)

        val list = arrayListOf("항목 1", "항목 2", "항목 3")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp1.adapter = adapter

        sp1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val sItem = parent?.getItemAtPosition(position).toString()
                Log.e(tag, "선택 항목:$sItem")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}