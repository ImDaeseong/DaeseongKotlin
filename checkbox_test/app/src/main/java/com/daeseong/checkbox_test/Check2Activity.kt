package com.daeseong.checkbox_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast

class Check2Activity : AppCompatActivity() {

    private val tag: String = Check2Activity::class.java.simpleName

    private var checkBox2: CheckBox? = null
    private var checkBox3: CheckBox? = null
    private var checkBox4: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check2)

        checkBox2 = findViewById<CheckBox>(R.id.checkBox2)
        checkBox2!!.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                Toast.makeText(this, "checked", Toast.LENGTH_SHORT).show()
            }
        }

        checkBox3 = findViewById<CheckBox>(R.id.checkBox3)
        checkBox3!!.setOnCheckedChangeListener{compoundButton, isChecked ->
            if(isChecked){
                Toast.makeText(this, "checked", Toast.LENGTH_SHORT).show()
            }
        }

        checkBox4 = findViewById<CheckBox>(R.id.checkBox4)
        checkBox4!!.setOnCheckedChangeListener{ _, isChecked ->

            if(isChecked){
                Toast.makeText(this, "checked", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
