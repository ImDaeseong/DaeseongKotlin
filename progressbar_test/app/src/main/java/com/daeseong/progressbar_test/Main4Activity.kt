package com.daeseong.progressbar_test

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class Main4Activity : AppCompatActivity() {

    private val tag: String = Main4Activity::class.java.simpleName;

    private var pb1: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        pb1 = findViewById<ProgressBar>(R.id.pb1)
        pb1!!.setOnClickListener(View.OnClickListener {
            pb1!!.visibility = View.GONE
        })

    }
}
