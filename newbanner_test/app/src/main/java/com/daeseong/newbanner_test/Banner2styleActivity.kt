package com.daeseong.newbanner_test

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.newbanner_test.Banner2_style.BannerView

class Banner2styleActivity : AppCompatActivity() {

    private val tag = Banner2styleActivity::class.java.simpleName

    private var bannerView1: BannerView? = null
    private var bannerView2: BannerView? = null
    private var bannerView3: BannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner2style)

        val linearLayout1: LinearLayout = findViewById(R.id.linearLayout1)
        bannerView1 = BannerView(this, "https://m.naver.com/", 0, 0)
        linearLayout1.addView(bannerView1, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))

        val linearLayout2: LinearLayout = findViewById(R.id.linearLayout2)
        bannerView2 = BannerView(this, "https://m.naver.com/", 300, 50)
        bannerView2!!.gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
        linearLayout2.addView(bannerView2, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))

        val linearLayout3: LinearLayout = findViewById(R.id.linearLayout3)
        bannerView3 = BannerView(this, "https://m.naver.com/", 0, 0)
        linearLayout3.addView(bannerView3, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))
    }
}
