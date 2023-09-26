package com.daeseong.animateslide_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FirstActivity : AppCompatActivity() {

    private val tag: String = FirstActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        title = "first";
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    private fun onCloseActivity() {
        finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }
}
