package com.daeseong.marquee_test

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Handler
import android.view.View
import android.widget.TextView
import java.util.*

class MarqueeTask(tv: TextView) : TimerTask() {

    private var handler: Handler? = null
    private var tv: TextView? = null
    private var bFlag = false

    init {
        this.tv = tv
        handler = Handler()
    }

    override fun run() {

        handler!!.post {
            marquee(tv, bFlag)
        }
    }

    private fun marquee(view: View?, bFlag: Boolean) {

        if (bFlag) {

            val animator1 = ObjectAnimator.ofFloat(view, "translationX", 0f, -1000f)
            animator1.duration = 500
            val animator2 = ObjectAnimator.ofFloat(view, "translationX", 1000f, 0f)
            animator2.duration = 500
            val animatorSet = AnimatorSet()
            animatorSet.playSequentially(animator1, animator2)
            animatorSet.start()

        } else {

            val animator1 = ObjectAnimator.ofFloat(view, "translationX", 0f, 1000f)
            animator1.duration = 500
            val animator2 = ObjectAnimator.ofFloat(view, "translationX", -1000f, 0f)
            animator2.duration = 500
            val animatorSet = AnimatorSet()
            animatorSet.playSequentially(animator1, animator2)
            animatorSet.start()
        }

        this.bFlag = !bFlag
    }
}