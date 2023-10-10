package com.daeseong.imageviewgradient_test

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Main8Activity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var image1: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        image1 = findViewById(R.id.image1)

        // 전체 투명도(80%)
        image1.alpha = 0.8f
    }
}
