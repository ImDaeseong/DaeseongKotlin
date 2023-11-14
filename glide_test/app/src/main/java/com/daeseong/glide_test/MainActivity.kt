package com.daeseong.glide_test

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var iv1: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv1 = findViewById<ImageView>(R.id.iv1)

        GlideUtil.load(this, iv1!!, "http://goo.gl/gEgYUd")

        //getImg()
    }

    private fun getImg() {
        Glide.with(this)
            .load("http://goo.gl/gEgYUd")
            .apply(
                RequestOptions().placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
            )
            .into(iv1!!)
    }
}
