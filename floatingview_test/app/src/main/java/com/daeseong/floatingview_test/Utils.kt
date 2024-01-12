package com.daeseong.floatingview_test

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.View

object Utils {
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dip(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue / scale + 0.5f).toInt()
    }

    fun slideingView(view: View) {
        val currentTranslationX = view.translationX

        val initialTranslationX = currentTranslationX + 1000f
        val finalTranslationX = currentTranslationX

        val animator1 = ObjectAnimator.ofFloat(view, "translationX", initialTranslationX, finalTranslationX)
        animator1.duration = 500

        val animatorSet = AnimatorSet()
        animatorSet.play(animator1)
        animatorSet.start()
    }
}