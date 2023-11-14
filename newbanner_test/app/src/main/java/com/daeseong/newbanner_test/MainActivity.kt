package com.daeseong.newbanner_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() , View.OnClickListener {

    private val tag: String = MainActivity::class.java.simpleName

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
            findViewById(R.id.button7)
        )

        buttons.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {

        val intentClass = when (v?.id) {
            R.id.button1 -> Banner1styleActivity::class.java
            R.id.button2 -> Banner2styleActivity::class.java
            R.id.button3 -> Banner3styleActivity::class.java
            R.id.button4 -> Banner4styleActivity::class.java
            R.id.button5 -> Banner5styleActivity::class.java
            R.id.button6 -> Banner6styleActivity::class.java
            R.id.button7 -> Banner7styleActivity::class.java
            else -> null
        }

        intentClass?.let {
            val intent = Intent(this, it)
            startActivity(intent)
        }
    }

}
