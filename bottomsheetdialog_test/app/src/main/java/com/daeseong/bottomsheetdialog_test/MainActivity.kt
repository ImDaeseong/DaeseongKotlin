package com.daeseong.bottomsheetdialog_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)

        setOnClickListeners(button1, Main1Activity::class.java)
        setOnClickListeners(button2, Main2Activity::class.java)
        setOnClickListeners(button3, Main3Activity::class.java)
        setOnClickListeners(button4, Main4Activity::class.java)
        setOnClickListeners(button5, Main5Activity::class.java)
        setOnClickListeners(button6, Main6Activity::class.java)
        setOnClickListeners(button7, Main7Activity::class.java)
        setOnClickListeners(button8, Main8Activity::class.java)
        setOnClickListeners(button9, Main9Activity::class.java){
            overridePendingTransition(R.anim.slide_in_bottom, 0)
        }
    }

    private fun setOnClickListeners(button: Button, activityClass: Class<*>, block: (() -> Unit)? = null) {
        button.setOnClickListener {
            val intent = Intent(this, activityClass)
            startActivity(intent)
            block?.invoke()
        }
    }

    override fun onClick(v: View?) {
        // This method is not used in the provided code
    }

}