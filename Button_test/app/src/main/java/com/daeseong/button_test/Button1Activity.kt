package com.daeseong.button_test

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Button1Activity : AppCompatActivity() {

    private val tag: String = Button1Activity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button1)
    }

    fun button1Click(v: View?) {
        Toast.makeText(this, "button1_Click", Toast.LENGTH_SHORT).show()
    }
}
