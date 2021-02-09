package com.daeseong.viewflipper_test

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity


class ViewFlipper3Activity : AppCompatActivity() {

    private val tag: String = ViewFlipper3Activity::class.java.simpleName

    private var viewFlipper1: ViewFlipper? = null

    private var Fade_in: Animation? = null
    private var Fade_out:Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_flipper3)

        title = tag

        viewFlipper1 = findViewById<ViewFlipper>(R.id.viewFlipper1)

        Fade_in = AnimationUtils.loadAnimation(this,android.R.anim.fade_in)
        Fade_out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out)

        viewFlipper1!!.inAnimation = Fade_in
        viewFlipper1!!.outAnimation = Fade_out

        viewFlipper1!!.isAutoStart = true
        viewFlipper1!!.flipInterval = 5000
        viewFlipper1!!.startFlipping()
    }
}
