package com.daeseong.newbanner_test

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

    private val imgs = intArrayOf(R.drawable.number1, R.drawable.number2, R.drawable.number3, R.drawable.number4)

    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            mCurrentPosition = viewPager?.currentItem ?: (0 + 1)
            viewPager?.currentItem = mCurrentPosition
            this.sendEmptyMessageDelayed(0, 2000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner6style)

        viewPager = findViewById(R.id.viewPager)
        viewPager?.addOnPageChangeListener(object : OnPageChangeListener {

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
        adapter.setOnItemClickListener(object : BannerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.e(tag, "Item Clicked: $position")
            }
        })

        // 롤링 개수
        val half = Short.MAX_VALUE / 2 % imgs.size

        Log.e(tag, "half:$half")

        viewPager?.currentItem = half
        viewPager?.adapter = adapter
        adapter.notifyDataSetChanged()
        handler.sendEmptyMessageDelayed(0, 2000)
    }

}
