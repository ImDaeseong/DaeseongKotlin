package com.daeseong.http_test


import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.http_test.HttpUtil.DownloadImage1


class ImageView2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view2)

        try {
            if (IsConnection()) {
                val url1 = "https://.png"
                val downloadImage1 = DownloadImage1(this)
                downloadImage1.execute(url1)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun ImageViewBitmap(bitmap: Bitmap?) {

        if (bitmap != null) {
            val imageView1: ImageView = findViewById<ImageView>(R.id.imageView1)
            imageView1.setImageBitmap(bitmap)
        } else {
            val imageView1: ImageView = findViewById<ImageView>(R.id.imageView1)
            imageView1.setImageResource(R.drawable.r)
        }
    }

    fun IsConnection(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo ?: return false
        if (!networkInfo.isConnected) {
            return false
        }
        return networkInfo.isAvailable
    }
}
