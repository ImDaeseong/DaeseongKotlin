package com.daeseong.marquee_test

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Handler
import android.os.Looper
import android.widget.TextView

class MarqueeTask(private val tv: TextView) : Runnable {

    private var bFlag = false
    private var animator: ValueAnimator? = null
    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun run() {
        marquee(tv, bFlag)
        handler.postDelayed(this, 5000)
    }

    private fun marquee(view: TextView, bFlag: Boolean) {
        val initialTranslationX = 0f
        val endTranslationX = if (bFlag) -1000f else 1000f

        animator?.cancel()

        animator = ValueAnimator.ofFloat(initialTranslationX, endTranslationX)
        animator?.duration = 1000
        animator?.addUpdateListener { valueAnimator ->
            view.translationX = valueAnimator.animatedValue as Float
        }

        animator?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                view.translationX = initialTranslationX
                this@MarqueeTask.bFlag = !bFlag
            }
        })

        animator?.start()
    }

    fun stop() {
        handler.removeCallbacks(this)
        animator?.cancel()
    }
}
