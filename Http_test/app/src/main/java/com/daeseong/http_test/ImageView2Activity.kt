package com.daeseong.http_test

import DownloadImage1
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ImageView2Activity : AppCompatActivity() {

    private lateinit var imageView1: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view2)

        imageView1 = findViewById(R.id.imageView1)

        if (isConnectionAvailable()) {
            val url = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
            val downloadImage1 = DownloadImage1(this)
            downloadImage1.execute(url)
        }
    }

    fun setImageViewBitmap(bitmap: Bitmap?) {
        val defaultBitmap = BitmapFactory.decodeResource(resources, R.drawable.r)
        imageView1.setImageBitmap(bitmap ?: defaultBitmap)
    }

    private fun isConnectionAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.run {
            isConnected && isAvailable
        } ?: false
    }
}
