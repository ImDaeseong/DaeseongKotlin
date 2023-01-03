package com.daeseong.newbanner_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.newbanner_test.Banner5_style.BannerView

class Banner5styleActivity : AppCompatActivity() {

    private val tag = Banner5styleActivity::class.java.simpleName

    private var bannerView5: BannerView? = null

    private val imgs = intArrayOf(R.drawable.number1, R.drawable.number2, R.drawable.number3, R.drawable.number4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner5style)

        bannerView5 = findViewById<BannerView>(R.id.mBannerView)
        bannerView5!!.setBannerData(imgs);
    }
}
