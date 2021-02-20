package com.daeseong.newbanner_test


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.newbanner_test.Banner4_style.BannerView


class Banner4styleActivity : AppCompatActivity() {

    private val tag = Banner4styleActivity::class.java.simpleName

    private var bannerView4: BannerView? = null

    private val imgs =
        intArrayOf(R.drawable.number1, R.drawable.number2, R.drawable.number3, R.drawable.number4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner4style)

        bannerView4 = findViewById<com.daeseong.newbanner_test.Banner4_style.BannerView>(R.id.mBannerView)
        bannerView4!!.setBannerData(imgs);
    }
}
