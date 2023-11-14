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
import androidx.core.graphics.drawable.toBitmap
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

        LayoutInflater.from(context).inflate(R.layout.banner1_style_view, this, true)
        banner1style = findViewById(R.id.banner1style_widget)
        banner1style?.setOnTouchListener(this)
    }

    fun setImage(bitmap: Bitmap?, nResourceID: Int) {
        banner1style?.setImageBitmap(bitmap ?: context.resources.getDrawable(nResourceID, null).toBitmap())
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {

        when (event.action) {

            MotionEvent.ACTION_UP -> {
                try {
                    (context as? Banner1styleActivity)?.let {
                        it.startActivity(Intent(it, MainActivity::class.java))
                        it.finish()
                    }
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
