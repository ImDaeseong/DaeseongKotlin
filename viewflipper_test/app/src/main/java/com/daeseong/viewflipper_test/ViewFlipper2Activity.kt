package com.daeseong.viewflipper_test

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity

class ViewFlipper2Activity : AppCompatActivity() {

    private val tag: String = ViewFlipper2Activity::class.java.simpleName

    private lateinit var button1: Button

    private lateinit var textview1: TextView
    private lateinit var textview2: TextView
    private lateinit var textview3: TextView

    private lateinit var viewFlipper1: ViewFlipper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_flipper2)

        title = tag

        textview1 = findViewById(R.id.textview1)
        textview2 = findViewById(R.id.textview2)
        textview3 = findViewById(R.id.textview3)

        viewFlipper1 = findViewById(R.id.viewFlipper1)

        textview1.text = "textview1textview1textview1textview1textview1textview1textview1textview1textview1"
        textview2.text = "textview2textview2textview2textview2textview2textview2textview2textview2textview2"
        textview3.text = "textview3textview3textview3textview3textview3textview3textview3textview3textview3"

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            viewFlipper1.showNext()
        }

    }
}
