package com.daeseong.imageviewgradient_test

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Main8Activity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var image1: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        image1 = findViewById<ImageView>(R.id.image1)

        //전체 투명도(80%)
        image1!!.alpha = 0.8f
    }
}
