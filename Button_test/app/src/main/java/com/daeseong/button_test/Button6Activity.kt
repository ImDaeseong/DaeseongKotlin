package com.daeseong.button_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Button6Activity : AppCompatActivity() {

    private val tag: String = Button6Activity::class.java.simpleName

    private lateinit var button10: Button
    private lateinit var button11: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button6)

        button10 = findViewById(R.id.button10)
        button11 = findViewById(R.id.button11)

        button10.setOnClickListener {
            showToast("button10_Click")
        }

        button11.setOnClickListener {
            showToast("button11_Click")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
