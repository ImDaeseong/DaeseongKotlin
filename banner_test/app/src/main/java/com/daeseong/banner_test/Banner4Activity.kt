package com.daeseong.banner_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Banner4Activity : AppCompatActivity() {

    private val tag = Banner4Activity::class.java.simpleName

    private var imageView1: ImageView? = null
    private var imageView2:ImageView? = null
    private var imageView3:ImageView? = null
    private var imageView4:ImageView? = null
    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner4)

        imageView1 = findViewById<ImageView>(R.id.imageView1)
        imageView2 = findViewById<ImageView>(R.id.imageView2)
        imageView3 = findViewById<ImageView>(R.id.imageView3)
        imageView4 = findViewById<ImageView>(R.id.imageView4)

        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener {

            Log.e(tag, "getCompleteBannerItem1:" + Banner_util.getInstance().getCompleteBannerItem1())

            val rowItems = Banner_util.getInstance().getDownloadBanner()
            for (i in rowItems.indices) {
                val bitmap = rowItems[i].bitmap

                when (i) {
                    0 -> {
                        imageView1!!.setImageBitmap(bitmap)
                    }
                    1 -> {
                        imageView2!!.setImageBitmap(bitmap)
                    }
                    2 -> {
                        imageView3!!.setImageBitmap(bitmap)
                    }
                    3 -> {
                        imageView4!!.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }
}
