package com.daeseong.http_test

import DownloadImage2
import DownloadJson1
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageTextView2Activity : AppCompatActivity() {

    private val tag: String = ImageTextView2Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var textView1: TextView
    private lateinit var imageView1: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_text_view2)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        textView1 = findViewById(R.id.textView1)
        imageView1 = findViewById(R.id.imageView1)

        button1.setOnClickListener {
            DownloadImage2(this).execute()
        }

        button2.setOnClickListener {
            DownloadJson1(this).execute()
        }
    }

    fun setImageViewBitmap(bitmap: Bitmap?) {

        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val defaultBitmap = BitmapFactory.decodeResource(resources, R.drawable.r)
                imageView1.setImageBitmap(bitmap ?: defaultBitmap)
            }
        }
    }

    fun loadJsonData(data: String?) {

        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                textView1.text = data
            }
        }
    }
}
