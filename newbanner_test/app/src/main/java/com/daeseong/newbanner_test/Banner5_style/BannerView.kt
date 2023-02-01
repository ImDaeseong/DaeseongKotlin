package com.daeseong.newbanner_test.Banner5_style

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.daeseong.newbanner_test.R

class BannerView : RelativeLayout {

    private val tag = BannerView::class.java.simpleName

    private var mViewPager: ViewPager? = null
    private var mAdapter: BannerAdapter? = null
    private var CurrentPosition = 0
    private var BannerDotView: ViewPagerIndicatorView? = null
    private var nSize = 0

    private val handler  = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            var item = mViewPager!!.currentItem
            item = if (nSize != 0) {
                (item + 1) % nSize
            } else {
                0
            }
            mViewPager!!.setCurrentItem(item, false)

            /*
            if(CurrentPosition > mViewPager!!.currentItem) {
                CurrentPosition = 0;
                mViewPager!!.setCurrentItem(CurrentPosition, false);
            }else {
                CurrentPosition = mViewPager!!.getCurrentItem() + 1;
                mViewPager!!.setCurrentItem(CurrentPosition, false);
                handler!!.sendEmptyMessageDelayed(0, 5000);
            }
            */
        }
    }

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
        LayoutInflater.from(getContext()).inflate(R.layout.banner5_style_view, this)
        mViewPager = findViewById<View>(R.id.banner5style_widget) as ViewPager
        BannerDotView = findViewById<View>(R.id.indicator) as ViewPagerIndicatorView

        initViewPager()

        mViewPager = findViewById<View>(R.id.banner5style_widget) as ViewPager
        mViewPager!!.addOnPageChangeListener(onPageChangeListener)
    }

    private fun initViewPager() {

        mAdapter = BannerAdapter(context, intArrayOf())
        mViewPager!!.adapter = mAdapter
        mAdapter!!.setOnItemClickListener(object : BannerAdapter.OnItemClickListener {

            override fun onItemClick(position: Int) {
                Log.e(tag, "onItemClick:$position")
            }
        })
    }

    fun setBannerData(bannerData: IntArray) {

        mAdapter!!.setData(bannerData)
        nSize = bannerData.size

        if (mAdapter!!.count > 1) {
            mViewPager!!.setCurrentItem(0, true)
            BannerDotView!!.init(mAdapter!!.count, R.drawable.dot_off, R.drawable.dot_on, 15)
            BannerDotView!!.setSelection(CurrentPosition)
            handler.sendEmptyMessageDelayed(0, 5000)
        }
    }

    private val onPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            handler.removeMessages(0)

            CurrentPosition = position % mAdapter!!.count
            BannerDotView!!.setSelection(CurrentPosition)
            handler.sendEmptyMessageDelayed(0, 5000)
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }

}