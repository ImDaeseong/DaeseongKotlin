package com.daeseong.banner_test

import ImageDownloader
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Banner3Activity : AppCompatActivity() {

    private val tag = Banner3Activity::class.java.simpleName

    private var imageDownloader: ImageDownloader? = null

    private var imageView1: ImageView? = null
    private var imageView2:ImageView? = null
    private var imageView3:ImageView? = null
    private var imageView4:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner3)

        imageView1 = findViewById<ImageView>(R.id.imageView1)
        imageView2 = findViewById<ImageView>(R.id.imageView2)
        imageView3 = findViewById<ImageView>(R.id.imageView3)
        imageView4 = findViewById<ImageView>(R.id.imageView4)

        Toast.makeText(MyApplication.getInstance(), "start ImageDownLoader", Toast.LENGTH_SHORT).show()

        val url1 = "https://.png"
        val url2 = "https://.png"
        val url3 = "https://.png"
        val url4 = "https://.png"

        imageDownloader = ImageDownloader(imageView1!!)
        imageDownloader!!.execute(url1)

        imageDownloader = ImageDownloader(imageView2!!)
        imageDownloader!!.execute(url2)

        imageDownloader = ImageDownloader(imageView3!!)
        imageDownloader!!.execute(url3)

        imageDownloader = ImageDownloader(imageView4!!)
        imageDownloader!!.execute(url4)
    }

    override fun onDestroy() {
        super.onDestroy()

        if (imageDownloader != null && imageDownloader!!.status != AsyncTask.Status.FINISHED) {
            imageDownloader!!.cancel(true)
        }
    }
}
