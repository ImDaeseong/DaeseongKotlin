package com.daeseong.viewflipper_test

import android.os.Bundle
import android.view.MotionEvent
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity


class ViewFlipper4Activity : AppCompatActivity() {

    private val tag: String = ViewFlipper4Activity::class.java.simpleName

    private var viewFlipper1: ViewFlipper? = null

    private var lastX = 0f
    private var currentX = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_flipper4)

        title = tag

        viewFlipper1 = findViewById<ViewFlipper>(R.id.viewFlipper1)
        viewFlipper1!!.startFlipping()
        viewFlipper1!!.flipInterval = 3000
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        viewFlipper1!!.stopFlipping()

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                //터치시작
                lastX = event.x
            }

            MotionEvent.ACTION_UP -> {
                //터치종료
                currentX = event.x

                //왼쪽 -> 오른쪽
                if (lastX < currentX) {

                    //첫번째화면이면
                    if (viewFlipper1!!.displayedChild != 0) {

                        //애니메이션 설정
                        viewFlipper1!!.setInAnimation(this, R.anim.in_from_left)
                        viewFlipper1!!.setOutAnimation(this, R.anim.out_to_right)
                        viewFlipper1!!.showNext()
                    }
                }

                //오른쪽 -> 왼쪽
                if (lastX > currentX) {

                    //첫번째화면이면
                    if (viewFlipper1!!.displayedChild != 1) {

                        //애니메이션 설정
                        viewFlipper1!!.setInAnimation(this, R.anim.in_from_right)
                        viewFlipper1!!.setOutAnimation(this, R.anim.out_to_left)
                        viewFlipper1!!.showPrevious()
                    }
                }
            }

        }
        return false
    }

}
