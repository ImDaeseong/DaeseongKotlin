package com.daeseong.rxjava3_test

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.rxjava3_test.Common.DownloadUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class Main5Activity : AppCompatActivity() {

    private val tag = Main5Activity::class.java.simpleName

    private lateinit var textView1: TextView
    private lateinit var imageView1: ImageView

    private val sUrl = "https://api.bithumb.com/public/ticker/BTC"
    private val sImgUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
    private val downloadUtil by lazy { DownloadUtil() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        textView1 = findViewById(R.id.textView1)
        imageView1 = findViewById(R.id.imageView1)

        fetchData()
        fetchImage()
    }

    private fun fetchData() {
        downloadUtil.getData(sUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    textView1.text = result
                },
                { error ->
                    Log.e(tag, "Error:", error)
                }
            )
    }

    private fun fetchImage() {
        downloadUtil.getBitmap(sImgUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { bitmap ->
                    imageView1.setImageBitmap(bitmap)
                },
                { error ->
                    Log.e(tag, "Error:", error)
                }
            )
    }
}
