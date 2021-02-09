package com.daeseong.viewflipper_test

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity


class ViewFlipper2Activity : AppCompatActivity() {

    private val tag: String = ViewFlipper2Activity::class.java.simpleName

    private var button1: Button? = null

    private var textview1: TextView? = null
    private var textview2: TextView? = null
    private var textview3: TextView? = null

    private var viewFlipper1: ViewFlipper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_flipper2)

        title = tag

        textview1 = findViewById<TextView>(R.id.textview1)
        textview2 = findViewById<TextView>(R.id.textview2)
        textview3 = findViewById<TextView>(R.id.textview3)

        viewFlipper1 = findViewById<ViewFlipper>(R.id.viewFlipper1)


        textview1!!.text = "textview1textview1textview1textview1textview1textview1textview1textview1textview1"
        textview2!!.text = "textview2textview2textview2textview2textview2textview2textview2textview2textview2"
        textview3!!.text = "textview3textview3textview3textview3textview3textview3textview3textview3textview3"


        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            viewFlipper1!!.showNext()
        }

    }
}
