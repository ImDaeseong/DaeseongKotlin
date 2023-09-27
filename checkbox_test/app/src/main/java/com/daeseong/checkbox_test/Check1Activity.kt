package com.daeseong.checkbox_test

import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity

class Check1Activity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private val tag: String = Check1Activity::class.java.simpleName

    private lateinit var checkBox1: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check1)

        checkBox1 = findViewById(R.id.checkBox1)
        checkBox1.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        checkBox1.text = if (isChecked) "checked" else "unchecked"
    }
}
