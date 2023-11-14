package com.daeseong.newbanner_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.newbanner_test.Banner3_style.BannerView

class Banner3styleActivity : AppCompatActivity() {

    private val tag = Banner3styleActivity::class.java.simpleName

    private var bannerView3: BannerView? = null

    private val imgs = intArrayOf(R.drawable.number1, R.drawable.number2, R.drawable.number3, R.drawable.number4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner3style)

        bannerView3 = findViewById(R.id.mBannerView)
        bannerView3?.setBannerData(imgs)
    }
}
