package com.daeseong.swipe_test

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class Swipe1Activity : AppCompatActivity() {

    private val tag = Swipe1Activity::class.java.simpleName

    private var iv1: ImageView? = null

    private var objectAnimator: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe1)

        iv1 = findViewById<ImageView>(R.id.iv1)
        iv1!!.setOnTouchListener(MySwipeListener(this))


        //x,y 사이즈 변경, 알파값 변경, 회전
        AnimatorUtil.Animato1(iv1!!)

        //알파값에 따른 변화
        //AnimatorUtil.Animato2(iv1!!)

        //커졌다 작아졌다 반복
        //AnimatorUtil.Animato3(iv1!!)

        //좌우 반복
        //AnimatorUtil.Animato4(iv1!!)

        //위 아래 반복
        //AnimatorUtil.Animato5(iv1!!)

        //360 도 회전후 원래대로
        //AnimatorUtil.Animato6(iv1!!)

        //360 도 회전 반복
        //AnimatorUtil.Animato7(iv1!!)

        //위에서 아래로
        //AnimatorUtil.Animato8(iv1!!)

        //아래에서 위로
        //AnimatorUtil.Animato9(iv1!!)

        //왼쪽으로 swipe
        //AnimatorUtil.Animato10(iv1!!)

        //오른쪽으로 swipe
        //AnimatorUtil.Animato11(iv1!!)
    }

    override fun onDestroy() {
        super.onDestroy()

        if (objectAnimator != null) {
            objectAnimator!!.cancel()
            objectAnimator = null
        }
    }

    inner class MySwipeListener(context: Context) :  SwipeListener(context) {

        override fun swipeUp(): Boolean {
            Log.e(tag, "swipeUp")
            return true
        }

        override fun swipeDown(): Boolean {
            Log.e(tag, "swipeDown")
            return true
        }

        override fun swipeLeft(): Boolean {
            Log.e(tag, "swipeLeft")
            return true
        }

        override fun swipeRight(): Boolean {
            Log.e(tag, "swipeRight")
            return true
        }
    }
}
