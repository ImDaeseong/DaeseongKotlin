package com.daeseong.http_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val tag: String = MainActivity::class.java.simpleName

    private val buttonIds = arrayOf(
        R.id.button1,
        R.id.button2,
        R.id.button3,
        R.id.button4,
        R.id.button5,
        R.id.button6,
        R.id.button7
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonIds.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        v?.let {
            val intent = when (it.id) {
                R.id.button1 -> Intent(this, ImageView1Activity::class.java)
                R.id.button2 -> Intent(this, ImageView2Activity::class.java)
                R.id.button3 -> Intent(this, TextView1Activity::class.java)
                R.id.button4 -> Intent(this, SendText1Activity::class.java)
                R.id.button5 -> Intent(this, ImageTextView2Activity::class.java)
                R.id.button6 -> Intent(this, TextView2Activity::class.java)
                R.id.button7 -> Intent(this, ImageTextView1Activity::class.java)
                else -> null
            }
            intent?.let {
                startActivity(intent)
            }
        }
    }
}
