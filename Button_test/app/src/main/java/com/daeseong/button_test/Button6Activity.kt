package com.daeseong.button_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

class Button6Activity : AppCompatActivity(), View.OnClickListener {

    private val tag: String = Button6Activity::class.java.simpleName

    private var button10 : Button? = null
    private var button11 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button6)

        button10 = findViewById<Button>(R.id.button10)
        button10!!.setOnClickListener(this)

        button11 = findViewById<Button>(R.id.button11)
        button11!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (v != null) {

            //var btn = v as Button
            //Log.e(tag, btn.text as String)

            when (v.id) {

                R.id.button10 -> {
                    Toast.makeText(this, "button10_Click", Toast.LENGTH_SHORT).show()
                }

                R.id.button11 -> {
                    Toast.makeText(this, "button11_Click", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
