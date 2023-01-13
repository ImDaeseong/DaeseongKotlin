package com.daeseong.edittext_test

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var et1: EditText? = null
    private var et2: EditText? = null
    private var et3: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et1 = findViewById<View>(R.id.et1) as EditText
        //et1!!.background.setColorFilter(Color.parseColor("#EDEDED"), PorterDuff.Mode.SRC_IN)
        et1!!.setText("")
        et1!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable) {

            }
        })

        et2 = findViewById<View>(R.id.et2) as EditText
        //et2!!.background.setColorFilter(Color.parseColor("#EDEDED"), PorterDuff.Mode.SRC_IN)
        et2!!.setText("")
        et2!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable) {

            }
        })

        et3 = findViewById<View>(R.id.et3) as EditText
        //et3!!.background.setColorFilter(Color.parseColor("#EDEDED"), PorterDuff.Mode.SRC_IN)
        et3!!.setText("")
        et3!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable) {

            }
        })

    }
}
