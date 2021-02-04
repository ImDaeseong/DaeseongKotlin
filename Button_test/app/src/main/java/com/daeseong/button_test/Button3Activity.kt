package com.daeseong.button_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Button3Activity : AppCompatActivity() {

    private val tag: String = Button3Activity::class.java.simpleName;

    private var button3 : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button3)

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener(button3Click())
    }

    private fun button3Click(): View.OnClickListener? = View.OnClickListener {

        //Log.e(tag, "button3_Click")

        Toast.makeText(this@Button3Activity, "button3_Click", Toast.LENGTH_SHORT).show()
    }

}
