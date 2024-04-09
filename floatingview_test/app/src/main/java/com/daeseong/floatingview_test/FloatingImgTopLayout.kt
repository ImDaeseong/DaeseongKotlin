package com.daeseong.floatingview_test

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.ImageView

class FloatingImgTopLayout(context: Context) {

    private val tag = FloatingImgTopLayout::class.simpleName

    private val floatingView: ImageView

    init {

        floatingView = ImageView(context)

        // 배경 이미지 설정
        floatingView.setImageResource(R.drawable.bgimg)

        val density = context.resources.displayMetrics.density
        val screenWidth = context.resources.displayMetrics.widthPixels

        val paramsIv = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)

        //넓이
        paramsIv.width = screenWidth - ((26 * density).toInt() + (11 * density).toInt())

        //높이
        val desiredHeight = (54 * density).toInt()
        paramsIv.height = (paramsIv.width.toFloat() / 283 * desiredHeight).toInt()

        floatingView.layoutParams = paramsIv

        //FIT_CENTER 비율을 유지하면서 뷰 크기에 맞게 조정
        floatingView.scaleType = ImageView.ScaleType.FIT_CENTER
        floatingView.clipToOutline = true
        floatingView.adjustViewBounds = true

        floatingView.setOnClickListener(object : OnSingleClickListener()  {
            override fun onSingleClick(view: View) {
                try {
                    hide()
                } catch (ex:Exception) {
                }
            }
        })
    }

    fun getFloatingView(): View {
        return floatingView
    }

    fun show(parent: ViewGroup, parentId: Int) {

        val pView = parent.findViewById<View>(parentId)
        val tbView = parent.findViewById<View>(R.id.cL1)

        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)

        //마진
        val density = parent.context.resources.displayMetrics.density
        layoutParams.leftMargin = (26 * density).toInt()
        layoutParams.rightMargin = (11 * density).toInt()

        pView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                pView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val tHeight = tbView.height
                val topMargin = pView.top + (tHeight - (12 * density).toInt())

                layoutParams.topMargin = topMargin
                floatingView.layoutParams = layoutParams
                parent.addView(floatingView)
            }
        })

    }

    fun hide() {
        try {
            (floatingView.parent as? ViewGroup)?.removeView(floatingView)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}
