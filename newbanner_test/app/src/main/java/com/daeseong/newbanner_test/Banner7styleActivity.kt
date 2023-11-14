package com.daeseong.newbanner_test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.daeseong.newbanner_test.Banner7_style.AutoScrollViewPager
import com.daeseong.newbanner_test.Banner7_style.ImagePagerAdapter
import com.daeseong.newbanner_test.Banner7_style.ViewPagerIndicatorView

class Banner7styleActivity : AppCompatActivity() {

    private val tag = Banner7styleActivity::class.java.simpleName

    private var banner1: AutoScrollViewPager? = null
    private var imagePagerAdapter: ImagePagerAdapter? = null

    private var indicator1: ViewPagerIndicatorView? = null
    private var indicator2: ViewPagerIndicatorView? = null

    private val nTotalCount = 4
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_banner7style)

        banner1 = findViewById<View>(R.id.banner1) as AutoScrollViewPager

        indicator1 = findViewById<View>(R.id.indicator1) as ViewPagerIndicatorView
        indicator2 = findViewById<View>(R.id.indicator2) as ViewPagerIndicatorView

        indicator1!!.init(nTotalCount, R.drawable.dot_off, R.drawable.select_on, 15)
        indicator1!!.setSelection(currentPosition)

        indicator2!!.init(nTotalCount, R.drawable.dot_off, R.drawable.select_on, 15)
        indicator2!!.setSelection(currentPosition)

        imagePagerAdapter = ImagePagerAdapter(this, nTotalCount).setInfiniteLoop(true)

        // 이미지 라운드 처리를 위한 설정
        banner1!!.clipToOutline = true
        banner1!!.adapter = imagePagerAdapter
        banner1!!.addOnPageChangeListener(MyOnPageChangeListener())
        banner1!!.setInterval(500)
        banner1!!.startAutoScroll()
        banner1!!.currentItem = Int.MAX_VALUE / 2 - Int.MAX_VALUE / 2 % nTotalCount

        imagePagerAdapter!!.setOnItemClickListener(object : ImagePagerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                try {
                    val nItem = position % nTotalCount
                    Log.e(tag, "nItem:$nItem")
                } catch (ex: java.lang.Exception) {
                    ex.message.toString()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        try {
            if (nTotalCount > 0) {
                banner1!!.startAutoScroll()
            }
        } catch (ex: Exception) {
            ex.message.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            banner1!!.stopAutoScroll()
        } catch (ex: Exception) {
            ex.message.toString()
        }
    }

    inner class MyOnPageChangeListener : OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            try {
                currentPosition = position % nTotalCount
                indicator1!!.setSelection(currentPosition)
                indicator2!!.setSelection(currentPosition)
            } catch (ex: Exception) {
                ex.message.toString()
            }
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageScrollStateChanged(arg0: Int) {

        }
    }
}
