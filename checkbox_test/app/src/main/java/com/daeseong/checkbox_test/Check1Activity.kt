package com.daeseong.checkbox_test

import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity


class Check1Activity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private val tag: String = Check1Activity::class.java.simpleName;

    private var checkBox1: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check1)

        checkBox1 = findViewById<CheckBox>(R.id.checkBox1)
        checkBox1!!.setOnCheckedChangeListener(this);
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

        if ( isChecked) {
            checkBox1!!.setText("checked");
        } else {
            checkBox1!!.setText("unchecked");
        }
    }

}
