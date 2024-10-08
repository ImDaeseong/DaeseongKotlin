package com.daeseong.newbanner_test.Banner4_style

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.daeseong.newbanner_test.Banner3_style.BannerView
import com.daeseong.newbanner_test.R

class BannerView : RelativeLayout {

    private val tag = BannerView::class.java.simpleName

    private var mViewPager: ViewPager? = null
    private var mAdapter: BannerAdapter? = null
    private var CurrentPosition = 0
    private var BannerDotView: ViewPagerIndicatorView? = null

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        inflate(context, R.layout.banner4_style_view, this)
        mViewPager = findViewById(R.id.banner4style_widget)
        BannerDotView = findViewById(R.id.indicator)

        initViewPager()

        mViewPager?.addOnPageChangeListener(onPageChangeListener)
    }

    private fun initViewPager() {
        mAdapter = BannerAdapter(context, intArrayOf())
        mViewPager?.adapter = mAdapter
        mAdapter?.setOnItemClickListener(object : BannerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.e(tag, "onItemClick:$position")
            }
        })
    }

    fun setBannerData(bannerData: IntArray?) {
        mAdapter?.setData(bannerData ?: intArrayOf())

        if (mAdapter?.count ?: 0 > 1) {
            mViewPager?.setCurrentItem(0, false)
            BannerDotView?.init(mAdapter?.count ?: 0, R.drawable.dot_off, R.drawable.dot_on, 15)
            BannerDotView?.setSelection(0)
        }
    }

    private val onPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            CurrentPosition = position % (mAdapter?.count ?: 1)
            BannerDotView?.setSelection(CurrentPosition)
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }
}