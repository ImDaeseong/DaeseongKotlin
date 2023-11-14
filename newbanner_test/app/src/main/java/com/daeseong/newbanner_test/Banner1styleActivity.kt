package com.daeseong.newbanner_test

import BannerDisplayImage
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.newbanner_test.Banner1_style.BannerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Banner1styleActivity : AppCompatActivity() {

    private val tag = Banner1styleActivity::class.java.simpleName

    private val bannerDisplayImage = BannerDisplayImage()
    private var bannerView1: BannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner1style)

        bannerView1 = findViewById(R.id.BannerView1)

        GlobalScope.launch(Dispatchers.Main) {

            // UI 스레드에서 비동기적으로 이미지 로드
            val bitmap: Bitmap? = bannerDisplayImage.loadImage("https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png")

            // UI에 비트맵 설정
            bannerView1?.setImage(bitmap, R.drawable.number1)
        }
    }
}
