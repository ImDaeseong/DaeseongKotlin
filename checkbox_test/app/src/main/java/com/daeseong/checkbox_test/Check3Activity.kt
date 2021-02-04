package com.daeseong.checkbox_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast

class Check3Activity : AppCompatActivity() {

    private val tag: String = Check3Activity::class.java.simpleName;

    private var checkBox5: CheckBox? = null
    private var checkBox6: CheckBox? = null
    private var checkBox7: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check3)

        checkBox5 = findViewById<CheckBox>(R.id.checkBox5)
        checkBox5!!.setOnCheckedChangeListener( object : CompoundButton.OnCheckedChangeListener{

            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                if (isChecked) {
                    Toast.makeText(this@Check3Activity, "checked", Toast.LENGTH_SHORT).show();
                }
            }
        } )

        checkBox6 = findViewById<CheckBox>(R.id.checkBox6)
        checkBox6!!.setOnCheckedChangeListener( object : CompoundButton.OnCheckedChangeListener{

            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                if (isChecked) {
                    Toast.makeText(this@Check3Activity, "checked", Toast.LENGTH_SHORT).show();
                }
            }
        } )

        checkBox7 = findViewById<CheckBox>(R.id.checkBox7)
        checkBox7!!.setOnCheckedChangeListener( object : CompoundButton.OnCheckedChangeListener{

            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                if (isChecked) {
                    Toast.makeText(this@Check3Activity, "checked", Toast.LENGTH_SHORT).show();
                }
            }
        } )

    }
}
