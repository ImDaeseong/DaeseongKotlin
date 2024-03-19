package com.daeseong.spinner_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout

class Spinner5Activity : AppCompatActivity() {

    private val tag = Spinner5Activity::class.java.simpleName

    private lateinit var atv1: AutoCompleteTextView
    private lateinit var tiL1: TextInputLayout

    private var list: List<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner5)

        tiL1 = findViewById(R.id.tiL1)
        atv1 = findViewById(R.id.atv1)

        atv1.setOnItemClickListener { parent, view, position, id ->
            val selected = list[position]
            Log.e(tag, selected)
        }

        atv1.setOnFocusChangeListener { v, hasFocus ->
            tiL1.hint = if (hasFocus) "" else "선택하세요"
        }

        atv1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                tiL1.hint = if (s.isNullOrEmpty()) "선택하세요" else ""
            }
        })

        getComboData()
    }

    override fun onDestroy() {
        super.onDestroy()
        list = emptyList()
    }

    private fun getComboData() {
        list = listOf("데이터1", "데이터2", "데이터3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
        atv1.setAdapter(adapter)
    }
}