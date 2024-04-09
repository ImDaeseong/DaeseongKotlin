package com.daeseong.floatingview_test

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView


class FloatingImgBottomLayout(context: Context) {

    private val tag = FloatingImgBottomLayout::class.simpleName

    private val floatingView: ImageView

    init {

        floatingView = ImageView(context)

        // 배경 이미지 설정
        floatingView.setImageResource(R.drawable.bgimg)

        val density = context.resources.displayMetrics.density
        val screenWidth = context.resources.displayMetrics.widthPixels

        val paramsIv = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)

        //넓이
        paramsIv.width = screenWidth - (16 * density * 2).toInt()

        //높이
        val desiredHeight = (60 * density).toInt()
        paramsIv.height = (paramsIv.width.toFloat() / 288 * desiredHeight).toInt()

        floatingView.layoutParams = paramsIv

        //FIT_CENTER 비율을 유지하면서 뷰 크기에 맞게 조정
        floatingView.scaleType = ImageView.ScaleType.FIT_CENTER
        floatingView.clipToOutline = true
        floatingView.adjustViewBounds = true

        //클릭 외부에서 처리
        /*
        floatingView.setOnClickListener(object : OnSingleClickListener()  {
            override fun onSingleClick(view: View) {
                try {
                    context.startActivity(Intent(context, FloatinView1Activity::class.java))
                    hide()
                } catch (ex:Exception) {
                }
            }
        })
        */
    }

    fun getFloatingView(): View {
        return floatingView
    }

    fun show(parent: ViewGroup) {

        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL

        // 마진 제거
        layoutParams.setMargins(0, 0, 0, 0)

        floatingView.layoutParams = layoutParams

        parent.addView(floatingView)
    }

    fun hide() {
        try {
            (floatingView.parent as? ViewGroup)?.removeView(floatingView)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}