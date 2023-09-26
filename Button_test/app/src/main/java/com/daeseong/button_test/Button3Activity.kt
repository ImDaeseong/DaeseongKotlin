package com.daeseong.button_test

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Button3Activity : AppCompatActivity() {

    private val tag: String = Button3Activity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button3)

        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            Toast.makeText(this, "button3_Click", Toast.LENGTH_SHORT).show()
        }
    }
}
