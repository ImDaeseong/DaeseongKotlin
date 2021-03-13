package com.daeseong.countdownlabel

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private var tv1: TextView? = null
    private var button1: Button? = null
    private var button2: Button? = null

    private val nCount = 10000
    private var countDownTimer: CountDownTimer? = null
    private val animatorSet = AnimatorSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        tv1 = findViewById(R.id.tv1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener {
            stop()
            start()
        }

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener {
            stop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stop()
    }

    private fun start() {

        countDownTimer = object : CountDownTimer(nCount.toLong(), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                try {
                    tv1!!.text = "현재 페이지 " + millisUntilFinished / 1000 + "초"
                    viewblink(tv1!!)
                } catch (ex: java.lang.Exception) {
                    ex.message.toString()
                }
            }

            override fun onFinish() {
                try {
                    tv1!!.alpha = 1f
                } catch (ex: java.lang.Exception) {
                    ex.message.toString()
                }
            }
        }
        countDownTimer!!.start()
    }

    private fun stop() {

        try {

            if (animatorSet != null) {
                animatorSet.removeAllListeners()
                animatorSet.end()
                animatorSet.cancel()
            }

            if (countDownTimer != null) countDownTimer!!.cancel()
            tv1!!.alpha = 1f

        } catch (ex: Exception) {
            ex.message.toString()
        }
    }

    private fun viewblink(view: View) {
        val fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        animatorSet.playTogether(fadeIn, fadeOut)
        animatorSet.duration = 1000
        animatorSet.start()
    }
}
