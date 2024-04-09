package com.daeseong.floatingview_test

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.ImageView
import com.daeseong.floatingview_test.Utils.dip2px

class FloatingDoubleImgTopLayout(context: Context) {

    private val tag = FloatingDoubleImgTopLayout::class.simpleName

    private val floatingView: FrameLayout

    private val bgImage : ImageView
    private val btnImage: ImageView

    init {
        floatingView = FrameLayout(context)

        btnImage = ImageView(context)

        // 배경 이미지 설정
        bgImage = ImageView(context)
        bgImage.setImageResource(R.drawable.bgimg)
        bgImage.scaleType = ImageView.ScaleType.FIT_CENTER
        bgImage.clipToOutline = true
        bgImage.adjustViewBounds = true
        floatingView.addView(bgImage)

        // 버튼 이미지 설정
        //btnImage.setImageResource(R.drawable.checkbox1)
        btnImage.setImageResource(android.R.color.transparent)
        btnImage.scaleType = ImageView.ScaleType.FIT_CENTER
        btnImage.clipToOutline = true
        btnImage.adjustViewBounds = true

        // 버튼 오른쪽 정렬
        val imgSize = dip2px(context, 50f)

        val btnLayoutParams = FrameLayout.LayoutParams(imgSize, FrameLayout.LayoutParams.MATCH_PARENT)
        btnLayoutParams.gravity = Gravity.END
        floatingView.addView(btnImage, btnLayoutParams)

        //투명 버튼 클릭
        btnImage.setOnClickListener(object : OnSingleClickListener()  {
            override fun onSingleClick(view: View) {
                try {
                    hide()
                } catch (ex:Exception) {
                }
            }
        })


        val density = context.resources.displayMetrics.density
        val screenWidth = context.resources.displayMetrics.widthPixels

        val paramsIv = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)

        //넓이
        paramsIv.width = screenWidth - ((26 * density).toInt() + (11 * density).toInt())

        //높이
        val desiredHeight = (54 * density).toInt()
        paramsIv.height = (paramsIv.width.toFloat() / 283 * desiredHeight).toInt()

        floatingView.layoutParams = paramsIv
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