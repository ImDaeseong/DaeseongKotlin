package com.daeseong.countdownlabel

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TextViewEx : AppCompatTextView {

    private var nSaveCount = 0
    private var nCount = 0
    private val animatorSet = AnimatorSet()
    private val textViewHandler = Handler(Looper.getMainLooper())

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun setCount(nCount: Int) {
        this.nSaveCount = nCount
    }

    fun startTimer() {
        textViewHandler.postDelayed({
            if (nCount > 0) {
                nCount--
                val sSecond = String.format("현재 페이지 %02d 초", nCount)
                text = sSecond
                viewBlink()
                startTimer()
            } else {
                stopTimer()
            }
        }, 1000)
    }

    fun stopTimer() {
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()

        textViewHandler.removeCallbacksAndMessages(null)
        alpha = 1f
        nCount = nSaveCount
    }

    private fun viewBlink() {
        val fadeOut = ObjectAnimator.ofFloat(this, "alpha", 1f, 0f)
        val fadeIn = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
        animatorSet.playTogether(fadeIn, fadeOut)
        animatorSet.duration = 1000
        animatorSet.start()
    }
}
