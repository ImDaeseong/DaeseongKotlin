package com.daeseong.animation_test

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Animate2Activity : AppCompatActivity() {

    private val tag: String = Animate2Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var image1: ImageView

    private var isShowing1 = true
    private var isShowing2 = true
    private var nType = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animate2)

        image1 = findViewById(R.id.image1)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        button1.setOnClickListener {

            isShowing1 = !isShowing1
            runAnimation1(image1, isShowing1)
        }

        button2.setOnClickListener {

            isShowing2 = !isShowing2
            runAnimation2(image1, isShowing2)
        }

        button3.setOnClickListener {

            runAnimation3(image1, nType)
            nType = if (nType < 4) nType + 1 else 1
        }

        button4.setOnClickListener {
            runAnimation4(image1)
        }
    }

    private fun runAnimation1(imageView: ImageView, bShow: Boolean) {

        val alphaValue = if (bShow) 1f else 0f
        val objectAnimator = ObjectAnimator.ofFloat(imageView, "alpha", imageView.alpha, alphaValue)
        objectAnimator.duration = 2000
        objectAnimator.interpolator = DecelerateInterpolator()
        objectAnimator.start()
    }

    private fun runAnimation2(imageView: ImageView, bShow: Boolean) {

        val alphaValue = if (bShow) 1f else 0f
        val scaleXValue = if (bShow) 1f else 0.618f
        val scaleYValue = if (bShow) 1f else 0.618f

        val alpha = ObjectAnimator.ofFloat(imageView, "alpha", imageView.alpha, alphaValue)
        val scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", imageView.scaleX, scaleXValue)
        val scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", imageView.scaleY, scaleYValue)

        val set = AnimatorSet()
        set.playTogether(alpha, scaleX, scaleY)
        set.interpolator = DecelerateInterpolator()
        set.duration = 2000
        set.start()
    }

    private fun runAnimation3(imageView: ImageView, nType: Int) {

        val translationX: Float
        val translationY: Float

        when (nType) {
            1 -> {
                translationX = -imageView.right.toFloat()
                translationY = 0f
            }
            2 -> {
                translationX = imageView.right.toFloat()
                translationY = 0f
            }
            3 -> {
                translationX = 0f
                translationY = imageView.right.toFloat()
            }
            4 -> {
                translationX = 0f
                translationY = -imageView.right.toFloat()
            }
            else -> {
                translationX = 0f
                translationY = 0f
            }
        }

        val objectAnimatorX = ObjectAnimator.ofFloat(imageView, "translationX", imageView.translationX, translationX)
        val objectAnimatorY = ObjectAnimator.ofFloat(imageView, "translationY", imageView.translationY, translationY)

        val animatorSet = AnimatorSet()
        animatorSet.interpolator = DecelerateInterpolator()
        animatorSet.duration = 2000
        animatorSet.playTogether(objectAnimatorX, objectAnimatorY)
        animatorSet.start()
    }

    fun runAnimation4(imageView: ImageView) {

        val animation = ScaleAnimation(
            0f, 1f, 0f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        animation.fillAfter = true
        animation.duration = 500
        imageView.startAnimation(animation)
    }
}
