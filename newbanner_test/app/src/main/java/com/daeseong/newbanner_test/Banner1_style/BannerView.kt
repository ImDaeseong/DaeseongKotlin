package com.daeseong.newbanner_test.Banner1_style


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.daeseong.newbanner_test.Banner1styleActivity
import com.daeseong.newbanner_test.MainActivity
import com.daeseong.newbanner_test.R


class BannerView : RelativeLayout, View.OnTouchListener {

    private val tag = BannerView::class.java.simpleName

    private var banner1style: ImageView? = null
    private var mContext: Context? = null

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet, 0) {
        initView(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {

        mContext = context

        val view: View = LayoutInflater.from(context).inflate(R.layout.banner1_style_view, this)
        banner1style = view.findViewById<ImageView>(R.id.banner1style_widget)
        banner1style!!.setOnTouchListener(this)
    }

    fun setImage(bitmap: Bitmap?, nResourceID: Int) {

        if (bitmap == null) {
            banner1style!!.setImageResource(nResourceID)
        } else {
            banner1style!!.setImageBitmap(bitmap)
        }
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {

        when (event.action) {

            MotionEvent.ACTION_UP -> {
                try {
                    (context as Banner1styleActivity).startActivity(Intent(context as Banner1styleActivity, MainActivity::class.java))
                    (context as Banner1styleActivity).finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            MotionEvent.ACTION_DOWN -> { }
            else -> {}
        }
        return true
    }


}
