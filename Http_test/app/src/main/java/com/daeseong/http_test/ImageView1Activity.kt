package com.daeseong.http_test

import DownloadImage
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ImageView1Activity : AppCompatActivity() {

    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView
    private lateinit var imageView4: ImageView
    private lateinit var imageView5: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view1)

        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)
        imageView4 = findViewById(R.id.imageView4)
        imageView5 = findViewById(R.id.imageView5)

        if (isConnection()) {
            val urls = arrayOf(
                "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png",
                "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png",
                "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png",
                "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png",
                "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
            )
            val imageViews = arrayOf(imageView1, imageView2, imageView3, imageView4, imageView5)

            for (i in urls.indices) {
                val downloadImage = DownloadImage(imageViews[i])
                downloadImage.execute(urls[i])
            }

        } else {

            val defaultImageResource = R.drawable.r
            imageView1.setBackgroundResource(defaultImageResource)
            imageView2.setBackgroundResource(defaultImageResource)
            imageView3.setBackgroundResource(defaultImageResource)
            imageView4.setBackgroundResource(defaultImageResource)
            imageView5.setBackgroundResource(defaultImageResource)
        }
    }

    private fun isConnection(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true && networkInfo.isAvailable
    }
}
