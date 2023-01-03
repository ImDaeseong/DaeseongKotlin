package com.daeseong.newbanner_test

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.newbanner_test.Banner2_style.BannerView

class Banner2styleActivity : AppCompatActivity() {

    private val tag = Banner2styleActivity::class.java.simpleName

    private var BannerView1: BannerView? = null
    private var BannerView2:BannerView? = null
    private var BannerView3:BannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner2style)

        val linearLayout1 = findViewById<LinearLayout>(R.id.linearLayout1)
        BannerView1 = BannerView(this, "https://m.naver.com/", 0, 0)
        linearLayout1.addView(BannerView1, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT) )

        val linearLayout2 = findViewById<LinearLayout>(R.id.linearLayout2)
        BannerView2 = BannerView(this, "https://m.naver.com/", 300, 50)
        BannerView2!!.gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
        linearLayout2.addView(BannerView2, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT) )

        val linearLayout3 = findViewById<LinearLayout>(R.id.linearLayout3)
        BannerView3 = BannerView(this, "https://m.naver.com/", 0, 0)
        linearLayout3.addView(BannerView3, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT) )

    }
}
