package com.daeseong.animation_test

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Animate1Activity : AppCompatActivity() {

    private val tag: String = Animate1Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var image1: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animate1)

        image1 = findViewById(R.id.image1)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        button1.setOnClickListener {
            runAnimation1(image1)
        }

        button2.setOnClickListener {
            runAnimation2(image1)
        }

        button3.setOnClickListener {
            runAnimation3(image1)
        }
    }

    private fun runAnimation1(imageView: ImageView) {

        //Y축으로 회전
        val objectAnimator = ObjectAnimator.ofFloat(imageView, "rotationY", 0f, 720f)
        //objectAnimator.setInterpolator(new LinearInterpolator());//일정한 속도
        //objectAnimator.setInterpolator(new AccelerateInterpolator());//점점 빠르게 속도
        //objectAnimator.setInterpolator(new DecelerateInterpolator());//점점 느리게 속도
        objectAnimator.duration = 2000 //2초동안 실행
        objectAnimator.start()
    }

    private fun runAnimation2(imageView: ImageView) {

        //x축으로 회전
        val objectAnimator = ObjectAnimator.ofFloat(imageView, "rotationX", 0f, 720f)
        //objectAnimator.setInterpolator(new LinearInterpolator());//일정한 속도
        //objectAnimator.setInterpolator(new AccelerateInterpolator());//점점 빠르게 속도
        //objectAnimator.setInterpolator(new DecelerateInterpolator());//점점 느리게 속도
        objectAnimator.duration = 2000 //2초동안 실행
        objectAnimator.start()
    }

    private fun runAnimation3(imageView: ImageView) {

        val objectAnimator = ObjectAnimator.ofFloat(imageView, "rotationY", 0f, 720f)
        objectAnimator.duration = 2000 //2초동안 실행
        objectAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {}
            override fun onAnimationEnd(animator: Animator) {

                //완료시 이미지 교체
                imageView.setImageResource(R.drawable.img2)
            }

            override fun onAnimationCancel(animator: Animator) {}
            override fun onAnimationRepeat(animator: Animator) {}
        })
        objectAnimator.start()
    }

}
