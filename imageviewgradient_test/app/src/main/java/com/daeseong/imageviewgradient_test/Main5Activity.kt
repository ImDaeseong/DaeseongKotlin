package com.daeseong.imageviewgradient_test

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Main5Activity : AppCompatActivity() {

    private val tag: String = Main5Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var image1: ImageViewEx5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        image1 = findViewById(R.id.image1)
        button1 = findViewById(R.id.button1)

        button1.setOnClickListener {
           image1.setImageResource(R.drawable.a)
           image1.setText("이미지에 텍스트 추가(텍스트 변경)")
        }
    }
}
