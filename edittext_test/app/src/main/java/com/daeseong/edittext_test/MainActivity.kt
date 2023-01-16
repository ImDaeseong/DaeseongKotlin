package com.daeseong.edittext_test

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var et1: EditText? = null
    private var et2: EditText? = null
    private var et3: EditText? = null
    private var et4: EditText? = null
    private var et5: ExTextInputLayout? = null

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
        et3!!.setText("")
        et3!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable) {

            }
        })

        et4 = findViewById<View>(R.id.et4) as EditText
        et4!!.setText("")
        et4!!.background = resources.getDrawable(R.drawable.edittext_select)
        et4!!.setTextColor(Color.BLACK)
        et4!!.setHintTextColor(Color.parseColor("#AFAFAF"))
        et4!!.onFocusChangeListener =
            OnFocusChangeListener { view, b -> changeBackground(b, view) }

        et4!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable) {

            }
        })

        et5 = findViewById<View>(R.id.et5) as ExTextInputLayout
        et5!!.getEditInstance()!!.setText("")
        et5!!.getEditInstance()!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }
            override fun afterTextChanged(editable: Editable) {

            }
        })

    }

    private fun changeBackground(bSelect: Boolean, view: View) {
        val et = view as EditText
        if (bSelect) {
            et.background = resources.getDrawable(R.drawable.edittext_select)
        } else {
            et.background = resources.getDrawable(R.drawable.edittext_select_normal)
        }
    }
}
