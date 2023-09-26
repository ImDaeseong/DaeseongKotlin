package com.daeseong.button_test

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Button5Activity : AppCompatActivity() {

    private val tag: String = Button5Activity::class.java.simpleName

    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button5)

        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)

        val clickListenerAll: View.OnClickListener = View.OnClickListener { view ->
            val buttonText = when (view.id) {
                R.id.button5 -> "button5_Click"
                R.id.button6 -> "button6_Click"
                R.id.button7 -> "button7_Click"
                R.id.button8 -> "button8_Click"
                R.id.button9 -> "button9_Click"
                else -> ""
            }
            if (buttonText.isNotEmpty()) {
                Toast.makeText(this@Button5Activity, buttonText, Toast.LENGTH_SHORT).show()
            }
        }

        button5.setOnClickListener(clickListenerAll)
        button6.setOnClickListener(clickListenerAll)
        button7.setOnClickListener(clickListenerAll)
        button8.setOnClickListener(clickListenerAll)
        button9.setOnClickListener(clickListenerAll)
    }
}
