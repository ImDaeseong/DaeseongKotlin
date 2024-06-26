package com.daeseong.button_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button;
import android.widget.Toast;

class Button4Activity : AppCompatActivity() {

    private val tag: String = Button4Activity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button4)

        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            Toast.makeText(this, "button4_Click", Toast.LENGTH_SHORT).show()
        }
    }
}

