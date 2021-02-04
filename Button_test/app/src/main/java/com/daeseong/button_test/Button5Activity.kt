package com.daeseong.button_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Button5Activity : AppCompatActivity() {

    private val tag: String = Button5Activity::class.java.simpleName;

    private var button5 : Button? = null;
    private var button6 : Button? = null;
    private var button7 : Button? = null;
    private var button8 : Button? = null;
    private var button9 : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button5)

        button5 = findViewById<Button>(R.id.button5)
        button6 = findViewById<Button>(R.id.button6)
        button7 = findViewById<Button>(R.id.button7)
        button8 = findViewById<Button>(R.id.button8)
        button9 = findViewById<Button>(R.id.button9)

        /*
        button5!!.setOnClickListener(clickListener5)
        button6!!.setOnClickListener(clickListener6)
        button7!!.setOnClickListener(clickListener7)
        button8!!.setOnClickListener(clickListener8)
        button9!!.setOnClickListener(clickListener9)
        */

        button5!!.setOnClickListener(clickListenerAll)
        button6!!.setOnClickListener(clickListenerAll)
        button7!!.setOnClickListener(clickListenerAll)
        button8!!.setOnClickListener(clickListenerAll)
        button9!!.setOnClickListener(clickListenerAll)
    }

    private val clickListenerAll : View.OnClickListener = View.OnClickListener { view ->

        if (view != null) {

            //var btn = view as Button;
            //Log.e(tag, btn.text as String)

            when (view.id) {

                R.id.button5 -> {
                    Toast.makeText(this@Button5Activity, "button5_Click", Toast.LENGTH_SHORT).show()
                }

                R.id.button6 -> {
                    Toast.makeText(this@Button5Activity, "button6_Click", Toast.LENGTH_SHORT).show()
                }

                R.id.button7 -> {
                    Toast.makeText(this@Button5Activity, "button7_Click", Toast.LENGTH_SHORT).show()
                }

                R.id.button8 -> {
                    Toast.makeText(this@Button5Activity, "button8_Click", Toast.LENGTH_SHORT).show()
                }

                R.id.button9 -> {
                    Toast.makeText(this@Button5Activity, "button9_Click", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private val clickListener5 : View.OnClickListener = View.OnClickListener {
        Toast.makeText(this@Button5Activity, "button5_Click", Toast.LENGTH_SHORT).show()
    }

    private val clickListener6 : View.OnClickListener = View.OnClickListener {
        Toast.makeText(this@Button5Activity, "button6_Click", Toast.LENGTH_SHORT).show()
    }

    private val clickListener7 : View.OnClickListener = View.OnClickListener {
        Toast.makeText(this@Button5Activity, "button7_Click", Toast.LENGTH_SHORT).show()
    }

    private val clickListener8 : View.OnClickListener = View.OnClickListener {
        Toast.makeText(this@Button5Activity, "button8_Click", Toast.LENGTH_SHORT).show()
    }

    private val clickListener9 : View.OnClickListener = View.OnClickListener {
        Toast.makeText(this@Button5Activity, "button9_Click", Toast.LENGTH_SHORT).show()
    }
}
