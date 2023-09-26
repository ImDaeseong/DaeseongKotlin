package com.daeseong.animation_test

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Animate3Activity : AppCompatActivity() {

    private val tag: String = Animate3Activity::class.java.simpleName

    private var nWidth:Int = 0
    private var nHeight:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animate3)

        //해상도 사이즈
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels
        nHeight = displayMetrics.heightPixels

        val image1 = findViewById<ImageView>(R.id.image1)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)

        button1.setOnClickListener {
            runAnimation1(image1)
        }

        button2.setOnClickListener {
            runAnimation2(this@Animate3Activity, image1)
        }

        button3.setOnClickListener {
            runAnimation3(image1, 20, 5)
        }
    }

    private fun runAnimation1(imageView: ImageView) {

        var offset = dip2px(imageView.context, 20f).toFloat()
        val right1 = ObjectAnimator.ofFloat(imageView, "translationX", offset)
        val left1 = ObjectAnimator.ofFloat(imageView, "translationX", offset * (-0.7f).also {
            offset = it
        })
        val right2 = ObjectAnimator.ofFloat(imageView, "translationX", offset * (-0.7f).also {
            offset = it
        })
        val left2 = ObjectAnimator.ofFloat(imageView, "translationX", offset * (-0.7f).also {
            offset = it
        })
        val right3 = ObjectAnimator.ofFloat(imageView, "translationX", offset * (-0.7f).also {
            offset = it
        })
        val left3 = ObjectAnimator.ofFloat(imageView, "translationX", offset * (-0.7f).also {
            offset = it
        })
        val fixed = ObjectAnimator.ofFloat(imageView, "translationX", 0f)
        val shake = AnimatorSet()
        shake.play(right1).before(left1)
        shake.play(left1).before(right2)
        shake.play(right2).before(left2)
        shake.play(left2).before(right3)
        shake.play(right3).before(left3)
        shake.play(left3).before(fixed)
        shake.duration = 130
        shake.start()
    }

    private fun runAnimation2(context: Context, v: View) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.shake)
        animation.interpolator = CycleInterpolator(2F)
        v.startAnimation(animation)
    }

    private fun runAnimation3(v: View, duration: Int, offset: Int) {
        val anim: Animation = TranslateAnimation((-offset).toFloat(), offset.toFloat(), 0F, 0F)
        anim.duration = duration.toLong()
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = 5
        v.startAnimation(anim)
    }

    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
