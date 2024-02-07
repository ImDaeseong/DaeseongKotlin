package com.daeseong.popupgrip_test

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*

class Popup3Activity : AppCompatActivity() {

    private val tag = Popup3Activity::class.java.simpleName

    private lateinit var popup_layout: View
    private lateinit var grip: View

    private var nStartPopupY = 0//팝업창 클릭시 Y
    private var nStartGripY: Float = 0f//grip 클릭시 Y
    private var nLimit = 0//팝업창 시작 Y
    private var nGripY: Float = 0f//grip 시작 Y
    private var nloadPopupY = 0//팝어창 호출시 Y

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_popup3)

        setFinishOnTouchOutside(false)

        init()

        changeDisplay()
    }

    private fun changeDisplay() {

        // 레이아웃이 생성된 후에 화면 크기 구하기
        window.decorView.viewTreeObserver.addOnGlobalLayoutListener {

            //화면에서 2/3 이상 아래로 드래그하면 종료 시키기 위해 최초 실행시 높이값
            nLimit = (window.decorView.height * 2) / 3
            Log.e(tag, "nLimit:$nLimit")

            nloadPopupY = popup_layout.y.toInt()
            Log.e(tag, "nloadPopupY:$nloadPopupY")

            nGripY = grip.y
            Log.e(tag, "nGripY:$nGripY")

            //리스너를 제거하여 중복 호출 방지
            window.decorView.viewTreeObserver.removeOnGlobalLayoutListener {}
        }

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.attributes.windowAnimations = R.style.BottomPopupSlideTheme
        window.setGravity(Gravity.BOTTOM)
    }

    private fun init() {
        popup_layout = findViewById(R.id.popup_layout)
        grip = findViewById(R.id.grip)

        grip.setOnTouchListener { v, event ->
            when (event.action) {

                MotionEvent.ACTION_DOWN -> {
                    nStartPopupY = popup_layout.y.toInt()
                    nStartGripY = event.rawY
                }

                MotionEvent.ACTION_MOVE -> {

                    //뷰 위치 계산
                    val deltaY = event.rawY - nStartGripY

                    //현재 팝업창 위치 계산
                    val nCurrentPopupY = (nStartPopupY + deltaY).toInt()

                    if (nCurrentPopupY in nloadPopupY..nLimit) {
                        movePopup(nCurrentPopupY)
                    }
                }

                MotionEvent.ACTION_UP -> {

                    // 팝업창 위치가 nLimit 값의 2/3 이상이면 종료
                    if ((popup_layout.y - nStartPopupY) > (nLimit / 2)) {

                        val nY = window.decorView.height - (nGripY * 2).toInt()
                        popup_layout.animate().y(nY.toFloat()).setDuration(200).withEndAction {

                            Log.e(tag, "팝업창 아래로 이동")
                        }

                    } else {

                        Log.e(tag, "팝업창 원대래대로")
                        popup_layout.animate().y(nloadPopupY.toFloat()).setDuration(200).start()
                        grip.animate().y(nGripY).setDuration(200).start()

                        v.performClick()
                    }
                }
            }
            true
        }
    }

    // 팝업창 위치 이동
    private fun movePopup(nCurrentY: Int) {
        popup_layout.y = nCurrentY.toFloat()
    }
}