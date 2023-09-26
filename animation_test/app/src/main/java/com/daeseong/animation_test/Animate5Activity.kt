package com.daeseong.animation_test

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class Animate5Activity : AppCompatActivity() {

    private val tag: String = Animate5Activity::class.java.simpleName

    private var button1: Button? = null
    private var progressbar1: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animate5)

        progressbar1 = findViewById(R.id.progressbar1)

        button1 = findViewById(R.id.button1)
        button1?.setOnClickListener {
            runAnimation1(10)
        }
    }

    private fun runAnimation1(nPos: Int) {

        val valueAnimator = ValueAnimator.ofInt(0, nPos * 10)
        valueAnimator.duration = 1000
        valueAnimator.interpolator = AnticipateOvershootInterpolator()
        valueAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            Log.e(tag, "nColor:" + animatedValue / 10)
            progressbar1?.progress = animatedValue
        }
        valueAnimator.start()

        /*
        val objectAnimator = ObjectAnimator.ofInt(progressbar1!!, "progress", 0, nPos * 10)
        objectAnimator.duration = 1000
        objectAnimator.interpolator = AnticipateOvershootInterpolator()
        objectAnimator.start()
        objectAnimator.addUpdateListener { animation ->
            val progressDrawable = progressbar1!!.progressDrawable.mutate()
            val nColor = animation.animatedValue as Int
            Log.e(tag, "nColor:" + nColor / 10)
            progressDrawable.setColorFilter(resources.getColor(R.color.progressbar), PorterDuff.Mode.SRC_IN);
            progressbar1!!.progressDrawable = progressDrawable;
        }
        */
    }
}
