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

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null

    private var image1: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animate2)

        image1 = findViewById<ImageView>(R.id.image1)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            runAnimation1(image1!!, false)
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            runAnimation2(image1!!, true)
        }

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener {

            runAnimation3(image1!!, 1)
        }

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener {

            runAnimation4(image1!!)
        }
    }

    private fun runAnimation1(imageView: ImageView, bShow: Boolean) {
        if (bShow) {

            //점점 보이게
            val objectAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f)
            objectAnimator.duration = 2000
            objectAnimator.interpolator = DecelerateInterpolator()
            objectAnimator.start()
        } else {

            //점점 않보이게
            val objectAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f)
            objectAnimator.duration = 2000
            objectAnimator.interpolator = DecelerateInterpolator()
            objectAnimator.start()
        }
    }

    private fun runAnimation2(imageView: ImageView, bShow: Boolean) {
        if (bShow) {

            //점점 보이게
            //작은 사이즈에서 원래 사이즈 대로 커짐
            val alpha = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f)
            val scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.618f, 1f)
            val scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.618f, 1f)
            val set = AnimatorSet()
            set.playTogether(alpha, scaleX, scaleY)
            set.interpolator = DecelerateInterpolator()
            set.duration = 2000
            set.start()
        } else {

            //점점 않보이게
            //원래 사이즈에서 작은 사이즈 작아짐
            val alpha = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f)
            val scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", imageView.scaleX, 0.618f)
            val scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", imageView.scaleY, 0.618f)
            val set = AnimatorSet()
            set.playTogether(alpha, scaleX, scaleY)
            set.interpolator = DecelerateInterpolator()
            set.duration = 2000
            set.start()
        }
    }

    private fun runAnimation3(imageView: ImageView, nType: Int) {
        if (nType == 1) {

            //현재 위치에서 왼쪽으로 이동
            val objectAnimator = ObjectAnimator.ofFloat(
                imageView,
                "translationX",
                imageView.translationX,
                -imageView.right.toFloat()
            )
            objectAnimator.interpolator = DecelerateInterpolator()
            objectAnimator.duration = 2000
            objectAnimator.start()
        } else if (nType == 2) {

            //현재 위치에서 오른쪽으로 이동
            val objectAnimator = ObjectAnimator.ofFloat(
                imageView,
                "translationX",
                imageView.translationX,
                imageView.right.toFloat()
            )
            objectAnimator.interpolator = DecelerateInterpolator()
            objectAnimator.duration = 2000
            objectAnimator.start()
        } else if (nType == 3) {

            //현재 위치에서 아래쪽으로 이동
            val objectAnimator = ObjectAnimator.ofFloat(
                imageView,
                "translationY",
                imageView.translationX,
                imageView.right.toFloat()
            )
            objectAnimator.interpolator = DecelerateInterpolator()
            objectAnimator.duration = 2000
            objectAnimator.start()
        } else if (nType == 4) {

            //현재 위치에서 위쪽으로 이동
            val objectAnimator = ObjectAnimator.ofFloat(
                imageView,
                "translationY",
                imageView.translationX,
                -imageView.right.toFloat()
            )
            objectAnimator.interpolator = DecelerateInterpolator()
            objectAnimator.duration = 2000
            objectAnimator.start()
        }
    }

    fun runAnimation4(imageView: ImageView) {

        //점점 않보이게
        //작은 사이즈에서 점점 큰사이즈로
        val animation: Animation = ScaleAnimation(
            0f,
            1f,
            0f,
            1f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        animation.fillAfter =
            true //애니메이션 종료후 원래 위치로 돌아가지 않고 종료 디폴트 setFillAfter(false); 는 원래 위치로 돌아감
        animation.duration = 500
        imageView.startAnimation(animation)
    }
}
