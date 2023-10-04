package com.daeseong.tablayout_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttons = listOf(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        )

        buttons.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View) {
        val intent = when (v.id) {
            R.id.button1 -> Intent(this, Main1Activity::class.java)
            R.id.button2 -> Intent(this, Main2Activity::class.java)
            R.id.button3 -> Intent(this, Main3Activity::class.java)
            R.id.button4 -> Intent(this, Main4Activity::class.java)
            R.id.button5 -> Intent(this, Main5Activity::class.java)
            R.id.button6 -> Intent(this, Main6Activity::class.java)
            R.id.button7 -> Intent(this, Main7Activity::class.java)
            R.id.button8 -> Intent(this, Main8Activity::class.java)
            R.id.button9 -> Intent(this, Main9Activity::class.java)
            else -> null
        }

        intent?.let { startActivity(it) }
    }
}
