package com.daeseong.viewflipper_test

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity

class ViewFlipper3Activity : AppCompatActivity() {

    private val tag: String = ViewFlipper3Activity::class.java.simpleName

    private lateinit var viewFlipper1: ViewFlipper

    private lateinit var fade_in: Animation
    private lateinit var fade_out: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_flipper3)

        title = tag

        viewFlipper1 = findViewById(R.id.viewFlipper1)

        fade_in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        fade_out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)

        viewFlipper1.inAnimation = fade_in
        viewFlipper1.outAnimation = fade_out

        viewFlipper1.isAutoStart = true
        viewFlipper1.flipInterval = 5000
        viewFlipper1.startFlipping()
    }
}
