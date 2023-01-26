package com.daeseong.banner_test

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Banner3Activity : AppCompatActivity() {

    private val tag = Banner3Activity::class.java.simpleName

    private var imageDownLoader: ImageDownLoader? = null

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
        imageDownLoader = ImageDownLoader(imageView1!!)
        imageDownLoader!!.execute(url1)

        imageDownLoader = ImageDownLoader(imageView2!!)
        imageDownLoader!!.execute(url2)

        imageDownLoader = ImageDownLoader(imageView3!!)
        imageDownLoader!!.execute(url3)

        imageDownLoader = ImageDownLoader(imageView4!!)
        imageDownLoader!!.execute(url4)
    }

    override fun onDestroy() {
        super.onDestroy()

        if (imageDownLoader!!.status != AsyncTask.Status.FINISHED) {
            imageDownLoader!!.cancel(true)
        }
    }
}
