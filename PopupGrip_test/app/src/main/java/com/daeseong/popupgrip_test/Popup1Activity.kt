package com.daeseong.popupgrip_test

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity

class Popup1Activity : AppCompatActivity() {

    private val tag = Popup1Activity::class.java.simpleName

    private lateinit var popup_layout: View
    private lateinit var grip: View

    private var nStartPopupY = 0 // 팝업창 클릭시 Y
    private var nStartGripY = 0f // grip 클릭시 Y
    private var nLimit = 0 // 팝업창 시작 Y
    private var nGripY = 0f // grip 시작 Y

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_popup1)

        setFinishOnTouchOutside(false)

        init()

        changeDisplay()
    }

    private fun changeDisplay() {

        window.decorView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                //화면에서 2/3 이상 아래로 드래그하면 종료 시키기 위해 최초 실행시 높이값
                nLimit = (window.decorView.height * 2) / 3
                Log.e(tag, "nLimit:$nLimit")

                nGripY = grip.y
                Log.e(tag, "nGripY:$nGripY")

                //리스너를 제거하여 중복 호출 방지
                window.decorView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

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

                    Log.e(tag, "ACTION_DOWN 팝업창(nStartPopupY):$nStartPopupY")
                    Log.e(tag, "ACTION_DOWN 뷰(nStartGripY):$nStartGripY")
                }

                MotionEvent.ACTION_MOVE -> {

                    //뷰 위치 계산
                    val deltaY = event.rawY - nStartGripY

                    //현재 팝업창 위치 계산
                    val nCurrentPopupY = (nStartPopupY + deltaY).toInt()

                    // 현재 위치 nCurrentPopupY 값이 시작시 nStartPopupY 값보다 위로가면 않되므로 체크
                    if (nCurrentPopupY >= nStartPopupY) {
                        movePopup(nCurrentPopupY)
                    }
                }

                MotionEvent.ACTION_UP -> {

                    // 팝업창 위치가 nLimit 값의 2/3 이상이면 종료
                    if ((popup_layout.y - nStartPopupY) > (nLimit / 2)) {

                        // 아래로 슬라이딩하면서 종료
                        popup_layout.animate().y(popup_layout.height.toFloat()).setDuration(200).withEndAction {
                            finish()
                        }.start()

                    } else {

                        // 팝업창 시작 위치로 되돌리기
                        popup_layout.animate().y(nStartPopupY.toFloat()).setDuration(200).start()

                        // grip 원래 위치로 이동
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
