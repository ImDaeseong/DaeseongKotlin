package com.daeseong.swipe_test

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.LinearInterpolator

object AnimatorUtil {

    //x,y 사이즈 변경, 알파값 변경, 회전
    fun animate1(view: View) {
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.5f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.5f, 1f),
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f),
            ObjectAnimator.ofFloat(view, "rotation", 1080f, 720f, 360f, 0f)
        )
        animatorSet.duration = 1000
        animatorSet.start()
    }

    //알파값에 따른 변화
    fun animate2(view: View) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.2f)
        objectAnimator.duration = 1000
        objectAnimator.repeatMode = ValueAnimator.REVERSE
        objectAnimator.repeatCount = 1
        objectAnimator.start()
    }

    //커졌다 작아졌다 반복
    fun animate3(view: View) {
        val animatorSet = AnimatorSet()
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.8f, 1f)
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.8f, 1f)
        val fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        scaleX.repeatCount = -1
        scaleY.repeatCount = -1
        fadeOut.repeatCount = -1
        fadeIn.repeatCount = -1
        animatorSet.playTogether(
            scaleY,
            fadeIn,
            scaleX,
            fadeOut
        )
        animatorSet.duration = 1000
        animatorSet.start()
    }

    //좌우 반복
    fun animate4(view: View) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "TranslationX", -10f, 10f)
        objectAnimator.duration = 1000
        objectAnimator.repeatCount = -1
        objectAnimator.repeatMode = ObjectAnimator.REVERSE
        objectAnimator.start()
    }

    //위 아래 반복
    fun animate5(view: View) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "TranslationY", -10f, 10f)
        objectAnimator.duration = 1000
        objectAnimator.repeatCount = -1
        objectAnimator.repeatMode = ObjectAnimator.REVERSE
        objectAnimator.start()
    }

    //360 도 회전후 원래대로
    fun animate6(view: View) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f)
        objectAnimator.duration = 1000
        objectAnimator.repeatCount = 1
        objectAnimator.repeatMode = ObjectAnimator.REVERSE
        objectAnimator.start()
    }

    //360 도 회전 반복
    fun animate7(view: View) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 360f)
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.duration = 2000
        objectAnimator.repeatCount = ValueAnimator.INFINITE
        objectAnimator.start()
    }

    //위에서 아래로
    fun animate8(view: View) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "translationY", -1000f, -500f, -200f, -50f, 0f)
        objectAnimator.duration = 1000
        objectAnimator.start()
    }

    //아래에서 위로
    fun animate9(view: View) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "translationY", 1000f, 500f, 200f, 50f, 0f)
        objectAnimator.duration = 1000
        objectAnimator.start()
    }

    //왼쪽으로 swipe
    fun animate10(view: View) {
        val animator1 = ObjectAnimator.ofFloat(view, "translationX", 0f, -1000f)
        animator1.duration = 500
        val animator2 = ObjectAnimator.ofFloat(view, "translationX", 1000f, 0f)
        animator2.duration = 500
        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(animator1, animator2)
        animatorSet.start()
    }

    //오른쪽으로 swipe
    fun animate11(view: View) {
        val animator1 = ObjectAnimator.ofFloat(view, "translationX", 0f, 1000f)
        animator1.duration = 500
        val animator2 = ObjectAnimator.ofFloat(view, "translationX", -1000f, 0f)
        animator2.duration = 500
        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(animator1, animator2)
        animatorSet.start()
    }
}