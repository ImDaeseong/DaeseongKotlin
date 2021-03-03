package com.daeseong.http_test

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.http_test.HttpUtil.DownloadImage2
import com.daeseong.http_test.HttpUtil.DownloadJson1


class ImageTextView2Activity : AppCompatActivity() {

    private val tag: String = ImageTextView2Activity::class.java.simpleName

    private var button1: Button? = null
    private var button2:Button? = null
    private var imageView1: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_text_view2)

        button1 = findViewById<Button>(R.id.button1)
        button2 = findViewById<Button>(R.id.button2)
        imageView1 = findViewById<ImageView>(R.id.imageView1)

        button1!!.setOnClickListener {

            if (IsConnection()) {
                DownloadImage2(this).execute()
            } else {
                Toast.makeText(applicationContext, "Not Connect", Toast.LENGTH_SHORT).show()
            }
        }

        button2!!.setOnClickListener {

            if (IsConnection()) {
                DownloadJson1(this).execute()
            } else {
                Toast.makeText(applicationContext, "Not Connect", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun ImageViewBitmap(bitmap: Bitmap?) {

        if (bitmap != null) {
            val imageView1 = findViewById<ImageView>(R.id.imageView1)
            imageView1.setImageBitmap(bitmap)
        } else {
            val imageView1 = findViewById<ImageView>(R.id.imageView1)
            imageView1.setImageResource(R.drawable.r)
        }
    }

    fun LoadjsonData(sData: String?) {

        Log.e(tag, "LoadjsonData:$sData")
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
