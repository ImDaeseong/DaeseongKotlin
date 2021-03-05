package com.daeseong.roulette

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var imageview1: ImageView? = null

    var nRouletteCount = 6
    var startDegree = 0f
    var endDegree = 0f
    var divDegree = 0f
    var repeatDegree = 360f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageview1 = findViewById<View>(R.id.imageview1) as ImageView
        imageview1!!.setOnClickListener {

            startDegree = divDegree
            val rand = Random()
            val degree_rand = rand.nextInt(360)
            endDegree = startDegree + 360 * nRouletteCount + degree_rand
            divDegree = endDegree % 360

            //Log.e(tag, "degree_rand:$degree_rand")
            //Log.e(tag, "startDegree:$startDegree")
            //Log.e(tag, "endDegree:$endDegree")
            //Log.e(tag, "divDegree:$divDegree")

            val nResult = getResult(divDegree)
            when (nResult) {
                1 -> {
                    repeatDegree = 360f
                }
                2 -> {
                    repeatDegree = 300f
                }
                3 -> {
                    repeatDegree = 240f
                }
                4 -> {
                    repeatDegree = 180f
                }
                5 -> {
                    repeatDegree = 120f
                }
                6 -> {
                    repeatDegree = 60f
                }
            }
            //Log.e(tag, "nResult:$nResult")

            val object1 = ObjectAnimator.ofFloat(imageview1!!, "rotation", startDegree, endDegree)
            object1.interpolator = LinearInterpolator()//일정하게
            object1.duration = 1000
            object1.start()

            val object2 = ObjectAnimator.ofFloat(imageview1!!, "rotation", 0f, repeatDegree)
            object2.interpolator = LinearInterpolator()//일정하게
            object2.duration = 1000
            object2.start()

            /*
            val object1 = ObjectAnimator.ofFloat(imageview1!!, "rotation", startDegree, endDegree)
            val object2 = ObjectAnimator.ofFloat(imageview1!!, "rotation", 0f, repeatDegree)
            val animSet = AnimatorSet()
            animSet.playTogether(object1, object2)
            animSet.duration = 1000
            animSet.interpolator = AccelerateDecelerateInterpolator() //빨리 돌다가 점덤 천천히
            animSet.start()
            */
        }

    }

    private fun getResult(angle: Float): Int {

        /*
        4개 일 경우 90도씩 1:0 ~ 90, 2:90 ~ 180, 3:180 ~ 270, 4:270 ~ 360
        6개 일 경우 60도씩 1:0 ~ 60, 2:60 ~ 120, 3:120 ~ 180, 4:180 ~ 240, 5:240 ~ 300, 6:300 ~ 360
        */

        //테스트 이미지는 30도씩 왼쪽으로 틀어져있음 그래서 30도씩 왼쪽으로 30도 이동
        //1:330 ~ 30, 2:30 ~ 90, 3:90 ~ 150, 4:150 ~ 210, 5:210 ~ 270, 6:270 ~ 330

        var nResult = 0
        if (angle > 330 || angle <= 30) {
            nResult = 1
        } else if (angle > 30 && angle <= 90) {
            nResult = 2
        } else if (angle > 90 && angle <= 150) {
            nResult = 3
        } else if (angle > 150 && angle <= 210) {
            nResult = 4
        } else if (angle > 210 && angle <= 270) {
            nResult = 5
        } else if (angle > 270 && angle <= 330) {
            nResult = 6
        }
        return nResult
    }

}
