package com.daeseong.animation_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private val buttonIds = arrayOf(
        R.id.button1, R.id.button2, R.id.button3, R.id.button4,
        R.id.button5, R.id.button6, R.id.button7, R.id.button8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (buttonId in buttonIds) {
            findViewById<Button>(buttonId).setOnClickListener {
                val intent = when (buttonId) {
                    R.id.button1 -> Intent(this, Animate1Activity::class.java)
                    R.id.button2 -> Intent(this, Animate2Activity::class.java)
                    R.id.button3 -> Intent(this, Animate3Activity::class.java)
                    R.id.button4 -> Intent(this, Animate4Activity::class.java)
                    R.id.button5 -> Intent(this, Animate5Activity::class.java)
                    R.id.button6 -> Intent(this, Animate6Activity::class.java)
                    R.id.button7 -> Intent(this, Animate7Activity::class.java)
                    R.id.button8 -> Intent(this, Animate8Activity::class.java)
                    else -> null
                }
                intent?.let {
                    startActivity(it)
                }
            }
        }

    }
}
