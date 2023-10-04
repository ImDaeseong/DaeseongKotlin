package com.daeseong.edittext_test

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var et3: EditText
    private lateinit var et4: EditText
    private lateinit var et5: ExTextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et1 = findViewById(R.id.et1)
        initializeEditText(et1)

        et2 = findViewById(R.id.et2)
        initializeEditText(et2)

        et3 = findViewById(R.id.et3)
        initializeEditText(et3)

        et4 = findViewById(R.id.et4)
        initializeStyledEditText(et4)

        et5 = findViewById(R.id.et5)
        initializeTextInputLayout(et5)
    }

    private fun initializeEditText(editText: EditText) {
        editText.setText("")
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    private fun initializeStyledEditText(editText: EditText) {
        editText.setText("")
        editText.background = resources.getDrawable(R.drawable.edittext_select)
        editText.setTextColor(Color.BLACK)
        editText.setHintTextColor(Color.parseColor("#AFAFAF"))
        editText.onFocusChangeListener = OnFocusChangeListener { view, b -> changeBackground(b, view) }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    private fun initializeTextInputLayout(textInputLayout: ExTextInputLayout) {
        textInputLayout.getEditInstance()?.apply {
            setText("")
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }

                override fun afterTextChanged(editable: Editable) {

                }
            })
        }
    }

    private fun changeBackground(bSelect: Boolean, view: View) {
        val et = view as EditText
        et.background = if (bSelect) {
            ContextCompat.getDrawable(this, R.drawable.edittext_select)
        } else {
            ContextCompat.getDrawable(this, R.drawable.edittext_select_normal)
        }
    }
}
