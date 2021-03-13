package com.daeseong.countdownlabel

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import java.util.*

class TextViewEx : AppCompatTextView {

    private var nSaveCount = 0
    private var nCount = 0
    private var timer = Timer()
    private val animatorSet = AnimatorSet()

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {}

    fun setCount(nCount: Int) {
        nSaveCount = nCount
    }

    fun startTimer() {
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                handler.post {
                    if (nCount > 0) {
                        nCount--
                        val sSecond = String.format("현재 페이지 %02d 초", nCount)
                        text = sSecond
                        viewblink(this@TextViewEx)
                    } else {
                        stopTimer()
                    }
                }
            }
        }, 0, 1000)
    }

    fun stopTimer() {

        if (animatorSet != null) {
            animatorSet.removeAllListeners()
            animatorSet.end()
            animatorSet.cancel()
        }

        if (timer != null) {
            timer.cancel()
        }

        alpha = 1f
        nCount = nSaveCount
    }

    private fun viewblink(view: View) {
        val fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        animatorSet.playTogether(fadeIn, fadeOut)
        animatorSet.duration = 1000
        animatorSet.start()
    }
}

