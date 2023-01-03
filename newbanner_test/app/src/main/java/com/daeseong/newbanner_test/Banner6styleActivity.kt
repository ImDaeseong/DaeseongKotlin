package com.daeseong.newbanner_test

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.daeseong.newbanner_test.Banner6_style.BannerAdapter

class Banner6styleActivity : AppCompatActivity() {

    private val tag = Banner6styleActivity::class.java.simpleName

    private var viewPager: ViewPager? = null

    private var nCurrent = 0
    private val TotalCount = 0

    private var mCurrentPosition = 0

    private val imgs = intArrayOf(
        R.drawable.number1,
        R.drawable.number2,
        R.drawable.number3,
        R.drawable.number4
    )

    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {

            //viewPager!!.setCurrentItem(nCurrent%TotalCount, true)
            //nCurrent++;

            mCurrentPosition = viewPager!!.currentItem + 1
            viewPager!!.currentItem = mCurrentPosition
            this.sendEmptyMessageDelayed(0, 2000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner6style)

        viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageSelected(position: Int) {

                Log.e(tag, "position:$position")
                handler.removeMessages(0)
                handler.sendEmptyMessageDelayed(0, 2000)
            }
        })

        val adapter = BannerAdapter(this, imgs)

        //롤링 개수
        var half = Short.MAX_VALUE / 2
        half %= imgs.size

        Log.e(tag, "half:$half")

        viewPager!!.currentItem = half
        viewPager!!.adapter = adapter
        adapter.notifyDataSetChanged()
        handler.sendEmptyMessageDelayed(0, 2000)
    }

}
