package com.daeseong.calendar_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonIds = arrayOf(
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7
        )

        for (buttonId in buttonIds) {
            findViewById<Button>(buttonId).setOnClickListener {
                val intent = Intent(this, CallActivity(buttonId))
                startActivity(intent)
            }
        }
    }

    private fun CallActivity(buttonId: Int): Class<*> {
        return when (buttonId) {
            R.id.button1 -> Main1Activity::class.java
            R.id.button2 -> Main2Activity::class.java
            R.id.button3 -> Main3Activity::class.java
            R.id.button4 -> Main4Activity::class.java
            R.id.button5 -> Main5Activity::class.java
            R.id.button6 -> Main6Activity::class.java
            R.id.button7 -> Main7Activity::class.java
            else -> throw IllegalArgumentException("Unknown button clicked")
        }
    }
}
