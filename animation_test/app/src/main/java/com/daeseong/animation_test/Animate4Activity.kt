package com.daeseong.animation_test

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Animate4Activity : AppCompatActivity() {

    private val tag: String = Animate4Activity::class.java.simpleName

    private var button1: Button? = null
    private var button2: Button? = null
    private var cL1: View? = null
    private var nWidth: Int = 0
    private var nHeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animate4)

        //해상도 사이즈
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels
        nHeight = displayMetrics.heightPixels

        cL1 = findViewById(R.id.cL1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener {
            runAnimation1()
        }

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener {
            runAnimation2()
        }
    }

    private fun runAnimation1() {

        val fheight = nHeight.toFloat()
        cL1?.apply {
            visibility = View.VISIBLE
            translationY = fheight
            animate()
                .translationY(0f)
                .setDuration(350)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }

        /*
        //아래에서 위로 올라오면서 화면에 보여준다.
        val fheight = nHeight.toFloat()
        cL1!!.visibility = View.VISIBLE
        val objectAnimator = ObjectAnimator.ofFloat(cL1!!, "translationY", fheight, 0f)
        objectAnimator.duration = 350
        objectAnimator.interpolator = DecelerateInterpolator()
        objectAnimator.start()
        */
    }

    private fun runAnimation2() {

        val fheight = nHeight.toFloat()
        cL1?.animate()
            ?.translationY(fheight)
            ?.setDuration(350)
            ?.setInterpolator(AccelerateInterpolator())
            ?.start()

        /*
        //현재 위치에서 아래로 내려가면서 숨긴다
        val fheight = nHeight.toFloat()
        val objectAnimator = ObjectAnimator.ofFloat(cL1!!, "translationY", 0f, fheight)
        objectAnimator.duration = 350
        objectAnimator.interpolator = AccelerateInterpolator()
        objectAnimator.start()
        */
    }
}
