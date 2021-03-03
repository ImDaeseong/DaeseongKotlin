package com.daeseong.http_test


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.http_test.HttpUtil.DownloadImage


class ImageView1Activity : AppCompatActivity() {

    private var downloadImage: DownloadImage? = null
    private var imageView1: ImageView? = null
    private var imageView2:ImageView? = null
    private var imageView3:ImageView? = null
    private var imageView4:ImageView? = null
    private var imageView5:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view1)

        imageView1 = findViewById<ImageView>(R.id.imageView1)
        imageView2 = findViewById<ImageView>(R.id.imageView2)
        imageView3 = findViewById<ImageView>(R.id.imageView3)
        imageView4 = findViewById<ImageView>(R.id.imageView4)
        imageView5 = findViewById<ImageView>(R.id.imageView5)

        try {

            if (IsConnection()) {

                val url1 = "https://.jpg"
                downloadImage = DownloadImage(imageView1!!)
                downloadImage!!.execute(url1)
                val url2 = "https://.png"
                downloadImage = DownloadImage(imageView2!!)
                downloadImage!!.execute(url2)
                val url3 = "https://.jpg"
                downloadImage = DownloadImage(imageView3!!)
                downloadImage!!.execute(url3)
                val url4 = "https://.jpg"
                downloadImage = DownloadImage(imageView4!!)
                downloadImage!!.execute(url4)
                val url5 = "https://.jpg"
                downloadImage = DownloadImage(imageView5!!)
                downloadImage!!.execute(url5)
            } else {

                imageView1!!.setBackgroundResource(R.drawable.r)
                imageView2!!.setBackgroundResource(R.drawable.r)
                imageView3!!.setBackgroundResource(R.drawable.r)
                imageView4!!.setBackgroundResource(R.drawable.r)
                imageView5!!.setBackgroundResource(R.drawable.r)
            }

        } catch (e: Exception) {

            downloadImage!!.cancel(true)
            imageView1!!.setBackgroundResource(R.drawable.r)
            imageView2!!.setBackgroundResource(R.drawable.r)
            imageView3!!.setBackgroundResource(R.drawable.r)
            imageView4!!.setBackgroundResource(R.drawable.r)
            imageView5!!.setBackgroundResource(R.drawable.r)
            e.printStackTrace()
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
