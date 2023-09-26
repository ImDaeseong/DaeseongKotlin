package com.daeseong.animation_test

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Animate6Activity : AppCompatActivity() {

    private val tag: String = Animate6Activity::class.java.simpleName

    private var button1: Button? = null
    private var button2: Button? = null

    private var image1: ImageView? = null
    private var animationDrawable: AnimationDrawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animate6)

        image1 = findViewById(R.id.image1)
        animationDrawable = image1?.drawable as? AnimationDrawable

        button1 = findViewById(R.id.button1)
        button1?.setOnClickListener {
            animationDrawable?.start()
        }

        button2 = findViewById(R.id.button2)
        button2?.setOnClickListener {
            animationDrawable?.stop()
        }

    }
}
