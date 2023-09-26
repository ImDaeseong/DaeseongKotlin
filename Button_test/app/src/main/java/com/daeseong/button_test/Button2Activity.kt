package com.daeseong.button_test

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Button2Activity : AppCompatActivity() {

    private val tag: String = Button2Activity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button2)

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            Toast.makeText(this, "button2_Click", Toast.LENGTH_SHORT).show()
        }
    }
}
