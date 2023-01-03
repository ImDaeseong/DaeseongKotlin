package com.daeseong.newbanner_test

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.newbanner_test.Banner1_style.BannerView

class Banner1styleActivity : AppCompatActivity() {

    private val tag = Banner1styleActivity::class.java.simpleName

    private var BannerView1: BannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner1style)

        BannerView1 = findViewById<BannerView>(R.id.BannerView1)

        var bitmap: Bitmap? = null
        try {

            bitmap = BannerdisplayImage().execute("https://.png").get()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        BannerView1!!.setImage(bitmap, R.drawable.number1)
    }
}
